package br.com.empresa.faturamento.dashboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.faturamento.dashboard.service.DashboardService;
import br.com.empresa.faturamento.shared.response.ApiResponse;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/resumo-geral")
    public ApiResponse<?> resumoGeral() {
        return ApiResponse.ok("Resumo geral carregado.", dashboardService.resumoGeral());
    }

    @GetMapping("/clientes-reajuste-pendente")
    public ApiResponse<?> clientesReajustePendente() {
        return ApiResponse.ok("Clientes com reajuste pendente carregados.", dashboardService.clientesReajustePendente());
    }

    @GetMapping("/clientes-por-faixa")
    public ApiResponse<?> clientesPorFaixa() {
        return ApiResponse.ok("Distribuicao por faixa carregada.", dashboardService.clientesPorFaixa());
    }

    @GetMapping("/evolucao-faturamento")
    public ApiResponse<?> evolucaoFaturamento() {
        return ApiResponse.ok("Evolucao de faturamento carregada.", dashboardService.evolucaoFaturamento());
    }

    @GetMapping("/honorario-atual-vs-recomendado")
    public ApiResponse<?> honorarioAtualVsRecomendado() {
        return ApiResponse.ok("Comparativo de honorarios carregado.", dashboardService.honorarioAtualVsRecomendado());
    }

    @GetMapping("/previsao-receita")
    public ApiResponse<?> previsaoReceita() {
        return ApiResponse.ok("Previsao de receita carregada.", dashboardService.previsaoReceita());
    }

    @GetMapping("/comparativo-periodos")
    public ApiResponse<?> comparativoPeriodos() {
        return ApiResponse.ok("Comparativo entre periodos carregado.", dashboardService.comparativoPeriodos());
    }
}
