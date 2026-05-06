package br.com.empresa.faturamento.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank(message = "Login obrigatorio.") String login,
    @NotBlank(message = "Senha obrigatoria.") String senha
) {
}
