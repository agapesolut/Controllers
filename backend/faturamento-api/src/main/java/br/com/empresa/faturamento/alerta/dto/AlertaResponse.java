package br.com.empresa.faturamento.alerta.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AlertaResponse(
    Long id,
    String clienteNome,
    String tipoAlerta,
    String status,
    BigDecimal faturamentoAtual,
    BigDecimal limiteFaixa,
    BigDecimal valorExcedido,
    BigDecimal honorarioAtual,
    BigDecimal honorarioSugerido,
    LocalDateTime criadoEm
) {
}
