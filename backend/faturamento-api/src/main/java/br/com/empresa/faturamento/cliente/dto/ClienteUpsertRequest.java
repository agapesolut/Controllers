package br.com.empresa.faturamento.cliente.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteUpsertRequest(
    @NotBlank(message = "Nome obrigatorio.") String nome,
    @NotBlank(message = "CNPJ obrigatorio.") String cnpj,
    @NotBlank(message = "Regime tributario obrigatorio.") String regimeTributario,
    @NotNull(message = "Honorario atual obrigatorio.") BigDecimal honorarioAtual,
    @NotNull(message = "Faixa atual obrigatoria.") Long faixaAtualId,
    @NotBlank(message = "Responsavel interno obrigatorio.") String responsavelInterno,
    @NotNull(message = "Status obrigatorio.") Boolean ativo
) {
}
