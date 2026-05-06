package br.com.empresa.faturamento.auditoria.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.faturamento.auditoria.service.AuditoriaService;
import br.com.empresa.faturamento.shared.response.ApiResponse;

@RestController
@RequestMapping("/api/auditoria")
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    public AuditoriaController(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    @GetMapping
    public ApiResponse<?> listar() {
        return ApiResponse.ok("Auditoria carregada com sucesso.", auditoriaService.listar());
    }

    @GetMapping("/usuario/{id}")
    public ApiResponse<?> listarPorUsuario(@PathVariable Long id) {
        return ApiResponse.ok("Auditoria por usuario carregada.", auditoriaService.listarPorUsuario(id));
    }

    @GetMapping("/entidade/{entidade}/{id}")
    public ApiResponse<?> listarPorEntidade(@PathVariable String entidade, @PathVariable Long id) {
        return ApiResponse.ok("Auditoria por entidade carregada.", auditoriaService.listarPorEntidade(entidade, id));
    }
}
