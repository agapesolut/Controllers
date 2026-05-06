package br.com.empresa.faturamento.faixa.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.faturamento.faixa.dto.FaixaUpsertRequest;
import br.com.empresa.faturamento.faixa.service.FaixaService;
import br.com.empresa.faturamento.shared.response.ApiResponse;

@RestController
@RequestMapping("/api/faixas")
public class FaixaController {

    private final FaixaService faixaService;

    public FaixaController(FaixaService faixaService) {
        this.faixaService = faixaService;
    }

    @GetMapping
    public ApiResponse<?> listar() {
        return ApiResponse.ok("Faixas carregadas com sucesso.", faixaService.listar());
    }

    @PostMapping
    public ApiResponse<?> criar(@Valid @RequestBody FaixaUpsertRequest request) {
        return ApiResponse.ok("Faixa criada em modo inicial mock.", faixaService.criar(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<?> atualizar(@PathVariable Long id, @Valid @RequestBody FaixaUpsertRequest request) {
        return ApiResponse.ok("Faixa atualizada em modo inicial mock.", faixaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> remover(@PathVariable Long id) {
        faixaService.remover(id);
        return ApiResponse.ok("Faixa removida em modo inicial mock.", null);
    }
}
