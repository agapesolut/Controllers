package br.com.empresa.faturamento.alerta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.faturamento.alerta.service.AlertaService;
import br.com.empresa.faturamento.shared.response.ApiResponse;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    private final AlertaService alertaService;

    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    @GetMapping
    public ApiResponse<?> listar() {
        return ApiResponse.ok("Alertas carregados com sucesso.", alertaService.listar());
    }

    @GetMapping("/resumo")
    public ApiResponse<?> resumo() {
        return ApiResponse.ok("Resumo de alertas carregado.", alertaService.resumo());
    }

    @GetMapping("/criticos")
    public ApiResponse<?> criticos() {
        return ApiResponse.ok("Alertas criticos carregados.", alertaService.criticos());
    }

    @GetMapping("/preventivos")
    public ApiResponse<?> preventivos() {
        return ApiResponse.ok("Alertas preventivos carregados.", alertaService.preventivos());
    }

    @PutMapping("/{id}/marcar-como-analisado")
    public ApiResponse<?> marcarComoAnalisado(@PathVariable Long id) {
        return ApiResponse.ok("Alerta marcado como analisado.", alertaService.marcarComoAnalisado(id));
    }
}
