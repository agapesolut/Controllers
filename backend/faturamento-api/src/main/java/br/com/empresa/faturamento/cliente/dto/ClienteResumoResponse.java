package br.com.empresa.faturamento.cliente.dto;

import java.math.BigDecimal;

public record ClienteResumoResponse(
    Long id,
    String nome,
    String cnpj,
    String responsavelInterno,
    BigDecimal faturamentoAtual,
    BigDecimal honorarioAtual,
    String faixaAtual,
    String statusAlerta
) {
}
