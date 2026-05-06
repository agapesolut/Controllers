package br.com.empresa.faturamento.auditoria.dto;

import java.time.LocalDateTime;

public record AuditoriaResponse(
    Long id,
    Long usuarioId,
    String acao,
    String modulo,
    String entidade,
    Long entidadeId,
    String valorAnterior,
    String valorNovo,
    LocalDateTime dataHora,
    String ip,
    String contexto
) {
}
