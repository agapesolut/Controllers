package br.com.empresa.faturamento.cliente.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.empresa.faturamento.cliente.dto.ClienteAnaliseResponse;
import br.com.empresa.faturamento.cliente.dto.ClienteResumoResponse;
import br.com.empresa.faturamento.cliente.dto.ClienteUpsertRequest;
import br.com.empresa.faturamento.cliente.entity.Cliente;
import br.com.empresa.faturamento.cliente.repository.ClienteRepository;
import br.com.empresa.faturamento.faixa.entity.FaixaHonorario;
import br.com.empresa.faturamento.faixa.service.FaixaService;
import br.com.empresa.faturamento.faturamento.service.FaturamentoService;
import br.com.empresa.faturamento.shared.enums.TipoAlerta;
import br.com.empresa.faturamento.shared.exception.ResourceNotFoundException;

@Service
public class ClienteService {

    private static final BigDecimal CEM = BigDecimal.valueOf(100);
    private static final BigDecimal DOZE = BigDecimal.valueOf(12);

    private final ClienteRepository clienteRepository;
    private final FaixaService faixaService;
    private final FaturamentoService faturamentoService;

    public ClienteService(
        ClienteRepository clienteRepository,
        FaixaService faixaService,
        FaturamentoService faturamentoService
    ) {
        this.clienteRepository = clienteRepository;
        this.faixaService = faixaService;
        this.faturamentoService = faturamentoService;
    }

    public List<ClienteResumoResponse> listar() {
        return listarEntidades().stream().map(this::toResumo).toList();
    }

    public ClienteResumoResponse buscarPorId(Long id) {
        return toResumo(buscarEntidadePorId(id));
    }

    public ClienteAnaliseResponse analisar(Long id) {
        Cliente cliente = buscarEntidadePorId(id);
        FaixaHonorario faixaAtual = faixaService.buscarEntidadePorId(cliente.getFaixaAtualId());
        BigDecimal faturamentoAtual = faturamentoService.faturamentoAtualOuZero(id);
        FaixaHonorario faixaSugerida = faixaService.encontrarFaixaPorFaturamento(faturamentoAtual);
        BigDecimal percentualUso = percentualUso(faturamentoAtual, faixaAtual.getFaturamentoMaximo());
        BigDecimal diferencaMensal = faixaSugerida.getValorHonorario().subtract(cliente.getHonorarioAtual()).max(BigDecimal.ZERO);

        return new ClienteAnaliseResponse(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCnpj(),
            cliente.getRegimeTributario(),
            cliente.getResponsavelInterno(),
            faturamentoAtual,
            faixaAtual.getNome(),
            faixaAtual.getFaturamentoMaximo(),
            percentualUso,
            faixaSugerida.getNome(),
            cliente.getHonorarioAtual(),
            faixaSugerida.getValorHonorario(),
            diferencaMensal,
            diferencaMensal.multiply(DOZE),
            determinarTipoAlerta(cliente).map(Enum::name).orElse("NORMAL"),
            faturamentoService.historicoPorCliente(id)
        );
    }

    public List<Cliente> listarEntidades() {
        return clienteRepository.findByAtivoTrue(Sort.by(Sort.Direction.ASC, "nome"));
    }

    public Cliente buscarEntidadePorId(Long id) {
        return clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente nao encontrado."));
    }

    public Optional<TipoAlerta> determinarTipoAlerta(Cliente cliente) {
        BigDecimal faturamentoAtual = faturamentoService.faturamentoAtualOuZero(cliente.getId());
        FaixaHonorario faixaAtual = faixaService.buscarEntidadePorId(cliente.getFaixaAtualId());

        if (faturamentoAtual.compareTo(BigDecimal.ZERO) <= 0) {
            return Optional.empty();
        }

        if (faturamentoAtual.compareTo(faixaAtual.getFaturamentoMaximo()) > 0) {
            return Optional.of(TipoAlerta.CRITICO);
        }

        BigDecimal limitePreventivo = faixaAtual.getFaturamentoMaximo()
            .multiply(faixaAtual.getPercentualAlertaPreventivo())
            .divide(CEM, 2, RoundingMode.HALF_UP);

        if (faturamentoAtual.compareTo(limitePreventivo) >= 0) {
            return Optional.of(TipoAlerta.PREVENTIVO);
        }

        return Optional.empty();
    }

    @Transactional
    public ClienteResumoResponse criar(ClienteUpsertRequest request) {
        faixaService.buscarEntidadePorId(request.faixaAtualId());

        Cliente novoCliente = new Cliente(
            null,
            request.nome(),
            request.cnpj(),
            request.regimeTributario(),
            request.honorarioAtual(),
            request.faixaAtualId(),
            request.responsavelInterno(),
            request.ativo()
        );
        return toResumo(clienteRepository.save(novoCliente));
    }

    @Transactional
    public ClienteResumoResponse atualizar(Long id, ClienteUpsertRequest request) {
        faixaService.buscarEntidadePorId(request.faixaAtualId());

        Cliente cliente = buscarEntidadePorId(id);
        cliente.atualizar(
            request.nome(),
            request.cnpj(),
            request.regimeTributario(),
            request.honorarioAtual(),
            request.faixaAtualId(),
            request.responsavelInterno(),
            request.ativo()
        );
        return toResumo(clienteRepository.save(cliente));
    }

    @Transactional
    public void remover(Long id) {
        Cliente cliente = buscarEntidadePorId(id);
        clienteRepository.delete(cliente);
    }

    private ClienteResumoResponse toResumo(Cliente cliente) {
        FaixaHonorario faixaAtual = faixaService.buscarEntidadePorId(cliente.getFaixaAtualId());
        BigDecimal faturamentoAtual = faturamentoService.faturamentoAtualOuZero(cliente.getId());

        return new ClienteResumoResponse(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCnpj(),
            cliente.getResponsavelInterno(),
            faturamentoAtual,
            cliente.getHonorarioAtual(),
            faixaAtual.getNome(),
            determinarTipoAlerta(cliente).map(Enum::name).orElse("NORMAL")
        );
    }

    private BigDecimal percentualUso(BigDecimal faturamentoAtual, BigDecimal limiteFaixaAtual) {
        return faturamentoAtual
            .multiply(CEM)
            .divide(limiteFaixaAtual, 2, RoundingMode.HALF_UP);
    }
}
