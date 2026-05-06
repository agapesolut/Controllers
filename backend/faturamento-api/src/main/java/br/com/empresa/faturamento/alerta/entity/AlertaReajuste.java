package br.com.empresa.faturamento.alerta.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.empresa.faturamento.shared.enums.StatusAlerta;
import br.com.empresa.faturamento.shared.enums.TipoAlerta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "alertas_reajuste")
public class AlertaReajuste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Column(name = "faixa_atual_id", nullable = false)
    private Long faixaAtualId;

    @Column(name = "faixa_sugerida_id", nullable = false)
    private Long faixaSugeridaId;

    @Column(name = "faturamento_atual", nullable = false, precision = 14, scale = 2)
    private BigDecimal faturamentoAtual;

    @Column(name = "honorario_atual", nullable = false, precision = 14, scale = 2)
    private BigDecimal honorarioAtual;

    @Column(name = "honorario_sugerido", nullable = false, precision = 14, scale = 2)
    private BigDecimal honorarioSugerido;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_alerta", nullable = false, length = 30)
    private TipoAlerta tipoAlerta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatusAlerta status;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    protected AlertaReajuste() {
    }

    public AlertaReajuste(
        Long id,
        Long clienteId,
        Long faixaAtualId,
        Long faixaSugeridaId,
        BigDecimal faturamentoAtual,
        BigDecimal honorarioAtual,
        BigDecimal honorarioSugerido,
        TipoAlerta tipoAlerta,
        StatusAlerta status,
        LocalDateTime criadoEm
    ) {
        this.id = id;
        this.clienteId = clienteId;
        this.faixaAtualId = faixaAtualId;
        this.faixaSugeridaId = faixaSugeridaId;
        this.faturamentoAtual = faturamentoAtual;
        this.honorarioAtual = honorarioAtual;
        this.honorarioSugerido = honorarioSugerido;
        this.tipoAlerta = tipoAlerta;
        this.status = status;
        this.criadoEm = criadoEm;
    }

    public void atualizarSnapshot(
        Long faixaAtualId,
        Long faixaSugeridaId,
        BigDecimal faturamentoAtual,
        BigDecimal honorarioAtual,
        BigDecimal honorarioSugerido,
        TipoAlerta tipoAlerta,
        StatusAlerta status,
        LocalDateTime criadoEm
    ) {
        this.faixaAtualId = faixaAtualId;
        this.faixaSugeridaId = faixaSugeridaId;
        this.faturamentoAtual = faturamentoAtual;
        this.honorarioAtual = honorarioAtual;
        this.honorarioSugerido = honorarioSugerido;
        this.tipoAlerta = tipoAlerta;
        this.status = status;
        this.criadoEm = criadoEm;
    }

    public void marcarComoAnalisado() {
        this.status = StatusAlerta.ANALISADO;
    }

    public Long getId() {
        return id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Long getFaixaAtualId() {
        return faixaAtualId;
    }

    public Long getFaixaSugeridaId() {
        return faixaSugeridaId;
    }

    public BigDecimal getFaturamentoAtual() {
        return faturamentoAtual;
    }

    public BigDecimal getHonorarioAtual() {
        return honorarioAtual;
    }

    public BigDecimal getHonorarioSugerido() {
        return honorarioSugerido;
    }

    public TipoAlerta getTipoAlerta() {
        return tipoAlerta;
    }

    public StatusAlerta getStatus() {
        return status;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
}
