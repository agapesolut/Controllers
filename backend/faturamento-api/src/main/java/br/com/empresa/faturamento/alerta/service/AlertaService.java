package br.com.empresa.faturamento.alerta.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.empresa.faturamento.alerta.dto.AlertaResponse;
import br.com.empresa.faturamento.alerta.dto.ResumoAlertasResponse;
import br.com.empresa.faturamento.alerta.entity.AlertaReajuste;
import br.com.empresa.faturamento.alerta.repository.AlertaRepository;
import br.com.empresa.faturamento.cliente.entity.Cliente;
import br.com.empresa.faturamento.cliente.service.ClienteService;
import br.com.empresa.faturamento.faixa.entity.FaixaHonorario;
import br.com.empresa.faturamento.faixa.service.FaixaService;
import br.com.empresa.faturamento.faturamento.service.FaturamentoService;
import br.com.empresa.faturamento.shared.enums.StatusAlerta;
import br.com.empresa.faturamento.shared.enums.TipoAlerta;
import br.com.empresa.faturamento.shared.exception.ResourceNotFoundException;

@Service
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final ClienteService clienteService;
    private final FaixaService faixaService;
    private final FaturamentoService faturamentoService;

    public AlertaService(
        AlertaRepository alertaRepository,
        ClienteService clienteService,
        FaixaService faixaService,
        FaturamentoService faturamentoService
    ) {
        this.alertaRepository = alertaRepository;
        this.clienteService = clienteService;
        this.faixaService = faixaService;
        this.faturamentoService = faturamentoService;
    }

    @Transactional
    public List<AlertaResponse> listar() {
        sincronizarAlertas();
        return alertaRepository.findAll(Sort.by(Sort.Direction.DESC, "criadoEm")).stream()
            .map(this::toResponse)
            .toList();
    }

    public ResumoAlertasResponse resumo() {
        List<AlertaResponse> alertas = listar();
        long criticos = alertas.stream().filter(alerta -> TipoAlerta.CRITICO.name().equals(alerta.tipoAlerta())).count();
        long preventivos = alertas.stream().filter(alerta -> TipoAlerta.PREVENTIVO.name().equals(alerta.tipoAlerta())).count();

        BigDecimal receitaPotencial = alertas.stream()
            .map(alerta -> alerta.honorarioSugerido().subtract(alerta.honorarioAtual()).max(BigDecimal.ZERO))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new ResumoAlertasResponse(alertas.size(), criticos, preventivos, receitaPotencial);
    }

    public List<AlertaResponse> criticos() {
        return listar().stream()
            .filter(alerta -> TipoAlerta.CRITICO.name().equals(alerta.tipoAlerta()))
            .toList();
    }

    public List<AlertaResponse> preventivos() {
        return listar().stream()
            .filter(alerta -> TipoAlerta.PREVENTIVO.name().equals(alerta.tipoAlerta()))
            .toList();
    }

    @Transactional
    public AlertaResponse marcarComoAnalisado(Long id) {
        AlertaReajuste alerta = alertaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Alerta nao encontrado."));
        alerta.marcarComoAnalisado();
        return toResponse(alertaRepository.save(alerta));
    }

    private final java.util.concurrent.atomic.AtomicBoolean syncing = new java.util.concurrent.atomic.AtomicBoolean(false);

    private void sincronizarAlertas() {
        if (!syncing.compareAndSet(false, true)) {
            return; // Já existe uma sincronização em andamento, ignoramos para evitar concorrência e exceptions no BD.
        }

        try {
            Set<Long> clientesComAlerta = new HashSet<>();

            for (Cliente cliente : clienteService.listarEntidades()) {
                clienteService.determinarTipoAlerta(cliente).ifPresent(tipoAlerta -> {
                    AlertaReajuste alerta = construirOuAtualizarAlerta(cliente, tipoAlerta);
                    clientesComAlerta.add(alerta.getClienteId());
                });
            }

            alertaRepository.findAll().stream()
                .filter(alerta -> !clientesComAlerta.contains(alerta.getClienteId()))
                .forEach(alertaRepository::delete);
                
            alertaRepository.flush(); // Garante que a transação grave antes de liberar o lock lógico
        } finally {
            syncing.set(false);
        }
    }


    private AlertaReajuste construirOuAtualizarAlerta(Cliente cliente, TipoAlerta tipoAlerta) {
        FaixaHonorario faixaAtual = faixaService.buscarEntidadePorId(cliente.getFaixaAtualId());
        BigDecimal faturamentoAtual = faturamentoService.faturamentoAtualOuZero(cliente.getId());
        FaixaHonorario faixaSugerida = faixaService.encontrarFaixaPorFaturamento(faturamentoAtual);
        LocalDateTime criadoEm = faturamentoService.faturamentoAtualEntidade(cliente.getId())
            .getDataReferencia()
            .atTime(9, 0);

        AlertaReajuste alerta = alertaRepository.findByClienteId(cliente.getId()).orElseGet(() -> new AlertaReajuste(
            null,
            cliente.getId(),
            faixaAtual.getId(),
            faixaSugerida.getId(),
            faturamentoAtual,
            cliente.getHonorarioAtual(),
            faixaSugerida.getValorHonorario(),
            tipoAlerta,
            StatusAlerta.PENDENTE_ANALISE,
            criadoEm
        ));

        boolean houveMudancaRelevante =
            alerta.getTipoAlerta() != tipoAlerta
                || alerta.getFaixaSugeridaId() == null
                || !alerta.getFaixaSugeridaId().equals(faixaSugerida.getId())
                || alerta.getFaturamentoAtual().compareTo(faturamentoAtual) != 0;

        StatusAlerta status = houveMudancaRelevante ? StatusAlerta.PENDENTE_ANALISE : alerta.getStatus();

        alerta.atualizarSnapshot(
            faixaAtual.getId(),
            faixaSugerida.getId(),
            faturamentoAtual,
            cliente.getHonorarioAtual(),
            faixaSugerida.getValorHonorario(),
            tipoAlerta,
            status,
            criadoEm
        );

        return alertaRepository.save(alerta);
    }

    private AlertaResponse toResponse(AlertaReajuste alerta) {
        Cliente cliente = clienteService.buscarEntidadePorId(alerta.getClienteId());
        FaixaHonorario faixaAtual = faixaService.buscarEntidadePorId(alerta.getFaixaAtualId());
        BigDecimal valorExcedido = alerta.getFaturamentoAtual().subtract(faixaAtual.getFaturamentoMaximo()).max(BigDecimal.ZERO);

        return new AlertaResponse(
            alerta.getId(),
            cliente.getNome(),
            alerta.getTipoAlerta().name(),
            alerta.getStatus().name(),
            alerta.getFaturamentoAtual(),
            faixaAtual.getFaturamentoMaximo(),
            valorExcedido,
            alerta.getHonorarioAtual(),
            alerta.getHonorarioSugerido(),
            alerta.getCriadoEm()
        );
    }
}
