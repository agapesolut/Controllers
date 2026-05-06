package br.com.empresa.faturamento.dashboard.dto;

import java.math.BigDecimal;

public record ChartPointResponse(
    String label,
    BigDecimal value
) {
}
