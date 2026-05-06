package br.com.empresa.faturamento.dashboard.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.empresa.faturamento.alerta.dto.AlertaResponse;
import br.com.empresa.faturamento.alerta.service.AlertaService;
import br.com.empresa.faturamento.cliente.service.ClienteService;
import br.com.empresa.faturamento.dashboard.dto.ChartPointResponse;
import br.com.empresa.faturamento.dashboard.dto.HonorarioComparacaoResponse;
import br.com.empresa.faturamento.dashboard.dto.ResumoGeralResponse;
import br.com.empresa.faturamento.faixa.service.FaixaService;
import br.com.empresa.faturamento.faturamento.service.FaturamentoService;
import br.com.empresa.faturamento.shared.enums.TipoAlerta;

@Service
public class DashboardService {

    private static final BigDecimal DOZE = BigDecimal.valueOf(12);

    private final ClienteService clienteService;
    private final AlertaService alertaService;
    private final FaixaService faixaService;
    private final FaturamentoService faturamentoService;

    public DashboardService(
        ClienteService clienteService,
        AlertaService alertaService,
        FaixaService faixaService,
        FaturamentoService faturamentoService
    ) {
        this.clienteService = clienteService;
        this.alertaService = alertaService;
        this.faixaService = faixaService;
        this.faturamentoService = faturamentoService;
    }

    public ResumoGeralResponse resumoGeral() {
        List<AlertaResponse> alertas = alertaService.listar();
        long alertasCriticos = alertas.stream().filter(alerta -> TipoAlerta.CRITICO.name().equals(alerta.tipoAlerta())).count();
        long alertasPreventivos = alertas.stream().filter(alerta -> TipoAlerta.PREVENTIVO.name().equals(alerta.tipoAlerta())).count();
        BigDecimal receitaPotencial = receitaPotencialMensal();

        return new ResumoGeralResponse(
            clienteService.listarEntidades().size(),
            alertasPreventivos,
            alertasCriticos,
            receitaPotencial,
            alertasCriticos
        );
    }

    public List<AlertaResponse> clientesReajustePendente() {
        return alertaService.listar();
    }

    public List<ChartPointResponse> clientesPorFaixa() {
        Map<String, Long> agrupado = clienteService.listarEntidades().stream()
            .collect(Collectors.groupingBy(
                cliente -> faixaService.encontrarFaixaPorFaturamento(faturamentoService.faturamentoAtualOuZero(cliente.getId())).getNome(),
                Collectors.counting()
            ));

        return agrupado.entrySet().stream()
            .map(entry -> new ChartPointResponse(entry.getKey(), BigDecimal.valueOf(entry.getValue())))
            .toList();
    }

    public List<ChartPointResponse> evolucaoFaturamento() {
        return faturamentoService.consolidadoPorPeriodo();
    }

    public List<HonorarioComparacaoResponse> honorarioAtualVsRecomendado() {
        return clienteService.listarEntidades().stream()
            .map(cliente -> {
                BigDecimal faturamentoAtual = faturamentoService.faturamentoAtualOuZero(cliente.getId());
                BigDecimal honorarioRecomendado = faixaService.encontrarFaixaPorFaturamento(faturamentoAtual).getValorHonorario();
                return new HonorarioComparacaoResponse(
                    cliente.getNome(),
                    cliente.getHonorarioAtual(),
                    honorarioRecomendado,
                    honorarioRecomendado.subtract(cliente.getHonorarioAtual()).max(BigDecimal.ZERO)
                );
            })
            .toList();
    }

    public List<ChartPointResponse> previsaoReceita() {
        BigDecimal mensal = receitaPotencialMensal();
        return List.of(
            new ChartPointResponse("Potencial mensal", mensal),
            new ChartPointResponse("Potencial anual", mensal.multiply(DOZE))
        );
    }

    public List<ChartPointResponse> comparativoPeriodos() {
        List<ChartPointResponse> pontos = faturamentoService.consolidadoPorPeriodo();
        int size = pontos.size();
        if (size < 2) {
            return pontos;
        }

        return List.of(
            new ChartPointResponse("Periodo anterior", pontos.get(size - 2).value()),
            new ChartPointResponse("Periodo atual", pontos.get(size - 1).value())
        );
    }

    private BigDecimal receitaPotencialMensal() {
        return honorarioAtualVsRecomendado().stream()
            .map(HonorarioComparacaoResponse::diferencaMensal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
