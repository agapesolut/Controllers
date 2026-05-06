package br.com.empresa.faturamento.faixa.dto;

import java.math.BigDecimal;

public record FaixaResponse(
    Long id,
    String nome,
    BigDecimal faturamentoMinimo,
    BigDecimal faturamentoMaximo,
    BigDecimal valorHonorario,
    BigDecimal percentualAlertaPreventivo,
    Boolean ativa
) {
}
