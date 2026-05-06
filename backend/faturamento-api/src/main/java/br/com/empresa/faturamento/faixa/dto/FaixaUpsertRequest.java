package br.com.empresa.faturamento.faixa.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FaixaUpsertRequest(
    @NotBlank(message = "Nome obrigatorio.") String nome,
    @NotNull(message = "Faturamento minimo obrigatorio.") BigDecimal faturamentoMinimo,
    @NotNull(message = "Faturamento maximo obrigatorio.") BigDecimal faturamentoMaximo,
    @NotNull(message = "Valor do honorario obrigatorio.") BigDecimal valorHonorario,
    @NotNull(message = "Percentual de alerta obrigatorio.") BigDecimal percentualAlertaPreventivo,
    @NotNull(message = "Status obrigatorio.") Boolean ativa
) {
}
