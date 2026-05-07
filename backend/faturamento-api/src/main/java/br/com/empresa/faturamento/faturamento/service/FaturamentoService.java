package br.com.empresa.faturamento.faturamento.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.empresa.faturamento.dashboard.dto.ChartPointResponse;
import br.com.empresa.faturamento.faturamento.dto.FaturamentoHistoricoResponse;
import br.com.empresa.faturamento.faturamento.entity.FaturamentoCliente;
import br.com.empresa.faturamento.faturamento.repository.FaturamentoRepository;
import br.com.empresa.faturamento.shared.exception.ResourceNotFoundException;

@Service
public class FaturamentoService {

    private static final DateTimeFormatter PERIOD_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    private final FaturamentoRepository faturamentoRepository;

    public FaturamentoService(FaturamentoRepository faturamentoRepository) {
        this.faturamentoRepository = faturamentoRepository;
    }

    public List<FaturamentoHistoricoResponse> historicoPorCliente(Long clienteId) {
        return faturamentoRepository.findByClienteIdOrderByDataReferenciaAsc(clienteId).stream()
            .map(item -> new FaturamentoHistoricoResponse(item.getDataReferencia(), item.getValorFaturado()))
            .toList();
    }

    public BigDecimal faturamentoAtual(Long clienteId) {
        return faturamentoAtualEntidade(clienteId).getValorFaturado();
    }

    public BigDecimal faturamentoAtualOuZero(Long clienteId) {
        return faturamentoRepository.findTopByClienteIdOrderByDataReferenciaDesc(clienteId)
            .map(FaturamentoCliente::getValorFaturado)
            .orElse(BigDecimal.ZERO);
    }

    public FaturamentoCliente faturamentoAtualEntidade(Long clienteId) {
        return faturamentoRepository.findTopByClienteIdOrderByDataReferenciaDesc(clienteId)
            .orElseThrow(() -> new ResourceNotFoundException("Faturamento do cliente nao encontrado."));
    }

    public List<ChartPointResponse> consolidadoPorPeriodo() {
        Map<LocalDate, BigDecimal> totais = new LinkedHashMap<>();
        faturamentoRepository.findAll(Sort.by(Sort.Direction.ASC, "dataReferencia")).stream()
            .forEach(item -> totais.merge(item.getDataReferencia(), item.getValorFaturado(), BigDecimal::add));

        return totais.entrySet().stream()
            .map(entry -> new ChartPointResponse(entry.getKey().format(PERIOD_FORMATTER), entry.getValue()))
            .toList();
    }

    public void adicionarFaturamento(Long clienteId, BigDecimal valorFaturado) {
        LocalDate dataReferencia = LocalDate.now().withDayOfMonth(1);
        FaturamentoCliente faturamento = new FaturamentoCliente(
            null,
            clienteId,
            dataReferencia.getMonthValue(),
            dataReferencia.getYear(),
            valorFaturado,
            dataReferencia
        );
        faturamentoRepository.save(faturamento);
    }

    public void upsertFaturamento(Long clienteId, BigDecimal valorFaturado) {
        LocalDate currentMonth = LocalDate.now().withDayOfMonth(1);
        Optional<FaturamentoCliente> existing =
            faturamentoRepository.findTopByClienteIdOrderByDataReferenciaDesc(clienteId);

        if (existing.isPresent() && existing.get().getDataReferencia().equals(currentMonth)) {
            FaturamentoCliente fc = existing.get();
            fc.setValorFaturado(valorFaturado);
            faturamentoRepository.save(fc);
        } else {
            adicionarFaturamento(clienteId, valorFaturado);
        }
    }
}
