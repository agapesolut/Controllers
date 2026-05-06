package br.com.empresa.faturamento.faturamento.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FaturamentoHistoricoResponse(
    LocalDate dataReferencia,
    BigDecimal valorFaturado
) {
}
