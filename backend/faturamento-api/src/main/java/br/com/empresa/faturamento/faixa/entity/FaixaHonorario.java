package br.com.empresa.faturamento.faixa.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "faixas_honorario")
public class FaixaHonorario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "faturamento_minimo", nullable = false, precision = 14, scale = 2)
    private BigDecimal faturamentoMinimo;

    @Column(name = "faturamento_maximo", nullable = false, precision = 14, scale = 2)
    private BigDecimal faturamentoMaximo;

    @Column(name = "valor_honorario", nullable = false, precision = 14, scale = 2)
    private BigDecimal valorHonorario;

    @Column(name = "percentual_alerta_preventivo", nullable = false, precision = 5, scale = 2)
    private BigDecimal percentualAlertaPreventivo;

    @Column(nullable = false)
    private Boolean ativa;

    protected FaixaHonorario() {
    }

    public FaixaHonorario(
        Long id,
        String nome,
        BigDecimal faturamentoMinimo,
        BigDecimal faturamentoMaximo,
        BigDecimal valorHonorario,
        BigDecimal percentualAlertaPreventivo,
        Boolean ativa
    ) {
        this.id = id;
        this.nome = nome;
        this.faturamentoMinimo = faturamentoMinimo;
        this.faturamentoMaximo = faturamentoMaximo;
        this.valorHonorario = valorHonorario;
        this.percentualAlertaPreventivo = percentualAlertaPreventivo;
        this.ativa = ativa;
    }

    public void atualizar(
        String nome,
        BigDecimal faturamentoMinimo,
        BigDecimal faturamentoMaximo,
        BigDecimal valorHonorario,
        BigDecimal percentualAlertaPreventivo,
        Boolean ativa
    ) {
        this.nome = nome;
        this.faturamentoMinimo = faturamentoMinimo;
        this.faturamentoMaximo = faturamentoMaximo;
        this.valorHonorario = valorHonorario;
        this.percentualAlertaPreventivo = percentualAlertaPreventivo;
        this.ativa = ativa;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getFaturamentoMinimo() {
        return faturamentoMinimo;
    }

    public BigDecimal getFaturamentoMaximo() {
        return faturamentoMaximo;
    }

    public BigDecimal getValorHonorario() {
        return valorHonorario;
    }

    public BigDecimal getPercentualAlertaPreventivo() {
        return percentualAlertaPreventivo;
    }

    public Boolean getAtiva() {
        return ativa;
    }
}
