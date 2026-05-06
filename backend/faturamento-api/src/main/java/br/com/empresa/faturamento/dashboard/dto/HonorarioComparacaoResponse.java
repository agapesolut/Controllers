package br.com.empresa.faturamento.dashboard.dto;

import java.math.BigDecimal;

public record HonorarioComparacaoResponse(
    String cliente,
    BigDecimal honorarioAtual,
    BigDecimal honorarioRecomendado,
    BigDecimal diferencaMensal
) {
}
