package br.com.empresa.faturamento.alerta.dto;

import java.math.BigDecimal;

public record ResumoAlertasResponse(
    long total,
    long criticos,
    long preventivos,
    BigDecimal receitaPotencialMensal
) {
}
