package br.com.empresa.faturamento.dashboard.dto;

import java.math.BigDecimal;

public record ResumoGeralResponse(
    long clientesMonitorados,
    long alertasPreventivos,
    long alertasCriticos,
    BigDecimal receitaPotencialMensal,
    long clientesAcimaDaFaixa
) {
}
