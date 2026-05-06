package br.com.empresa.faturamento.cliente.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.faturamento.cliente.dto.ClienteUpsertRequest;
import br.com.empresa.faturamento.cliente.service.ClienteService;
import br.com.empresa.faturamento.faturamento.service.FaturamentoService;
import br.com.empresa.faturamento.shared.response.ApiResponse;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final FaturamentoService faturamentoService;

    public ClienteController(ClienteService clienteService, FaturamentoService faturamentoService) {
        this.clienteService = clienteService;
        this.faturamentoService = faturamentoService;
    }

    @GetMapping
    public ApiResponse<?> listar() {
        return ApiResponse.ok("Clientes carregados com sucesso.", clienteService.listar());
    }

    @GetMapping("/{id}")
    public ApiResponse<?> buscarPorId(@PathVariable Long id) {
        return ApiResponse.ok("Cliente encontrado.", clienteService.buscarPorId(id));
    }

    @PostMapping
    public ApiResponse<?> criar(@Valid @RequestBody ClienteUpsertRequest request) {
        return ApiResponse.ok("Cliente criado em modo inicial mock.", clienteService.criar(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<?> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteUpsertRequest request) {
        return ApiResponse.ok("Cliente atualizado em modo inicial mock.", clienteService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> remover(@PathVariable Long id) {
        clienteService.remover(id);
        return ApiResponse.ok("Cliente removido em modo inicial mock.", null);
    }

    @GetMapping("/{id}/analise")
    public ApiResponse<?> analise(@PathVariable Long id) {
        return ApiResponse.ok("Analise do cliente carregada.", clienteService.analisar(id));
    }

    @GetMapping("/{id}/historico-faturamento")
    public ApiResponse<?> historicoFaturamento(@PathVariable Long id) {
        return ApiResponse.ok("Historico de faturamento carregado.", faturamentoService.historicoPorCliente(id));
    }
}
