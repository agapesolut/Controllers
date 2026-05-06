package br.com.empresa.faturamento.relatorio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.faturamento.relatorio.service.RelatorioService;
import br.com.empresa.faturamento.shared.response.ApiResponse;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/reajustes/excel")
    public ApiResponse<?> reajustesExcel() {
        return ApiResponse.ok("Relatorio solicitado.", relatorioService.exportar("reajustes-excel"));
    }

    @GetMapping("/reajustes/csv")
    public ApiResponse<?> reajustesCsv() {
        return ApiResponse.ok("Relatorio solicitado.", relatorioService.exportar("reajustes-csv"));
    }

    @GetMapping("/reajustes/pdf")
    public ApiResponse<?> reajustesPdf() {
        return ApiResponse.ok("Relatorio solicitado.", relatorioService.exportar("reajustes-pdf"));
    }

    @GetMapping("/receita-potencial/excel")
    public ApiResponse<?> receitaPotencialExcel() {
        return ApiResponse.ok("Relatorio solicitado.", relatorioService.exportar("receita-potencial-excel"));
    }

    @GetMapping("/comparativo-periodos/pdf")
    public ApiResponse<?> comparativoPeriodosPdf() {
        return ApiResponse.ok("Relatorio solicitado.", relatorioService.exportar("comparativo-periodos-pdf"));
    }
}
