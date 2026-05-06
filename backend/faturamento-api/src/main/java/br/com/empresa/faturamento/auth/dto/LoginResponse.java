package br.com.empresa.faturamento.auth.dto;

import java.util.Set;

public record LoginResponse(
    String nome,
    String email,
    String perfil,
    String token,
    Set<String> permissoes
) {
}
