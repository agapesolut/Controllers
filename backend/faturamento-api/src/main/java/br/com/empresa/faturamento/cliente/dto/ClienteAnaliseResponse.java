package br.com.empresa.faturamento.cliente.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.empresa.faturamento.faturamento.dto.FaturamentoHistoricoResponse;

public record ClienteAnaliseResponse(
    Long id,
    String nome,
    String cnpj,
    String regimeTributario,
    String responsavelInterno,
    BigDecimal faturamentoAtual,
    String faixaAtual,
    BigDecimal limiteFaixaAtual,
    BigDecimal percentualUsoFaixaAtual,
    String faixaSugerida,
    BigDecimal honorarioAtual,
    BigDecimal honorarioSugerido,
    BigDecimal diferencaMensal,
    BigDecimal diferencaAnualEstimada,
    String tipoAlerta,
    List<FaturamentoHistoricoResponse> historico
) {
}
