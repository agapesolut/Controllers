package br.com.empresa.faturamento.faturamento.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "faturamentos_clientes")
public class FaturamentoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Column(nullable = false)
    private Integer mes;

    @Column(nullable = false)
    private Integer ano;

    @Column(name = "valor_faturado", nullable = false, precision = 14, scale = 2)
    private BigDecimal valorFaturado;

    @Column(name = "data_referencia", nullable = false)
    private LocalDate dataReferencia;

    protected FaturamentoCliente() {
    }

    public FaturamentoCliente(
        Long id,
        Long clienteId,
        Integer mes,
        Integer ano,
        BigDecimal valorFaturado,
        LocalDate dataReferencia
    ) {
        this.id = id;
        this.clienteId = clienteId;
        this.mes = mes;
        this.ano = ano;
        this.valorFaturado = valorFaturado;
        this.dataReferencia = dataReferencia;
    }

    public Long getId() {
        return id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Integer getMes() {
        return mes;
    }

    public Integer getAno() {
        return ano;
    }

    public BigDecimal getValorFaturado() {
        return valorFaturado;
    }

    public void setValorFaturado(BigDecimal valorFaturado) {
        this.valorFaturado = valorFaturado;
    }

    public LocalDate getDataReferencia() {
        return dataReferencia;
    }
}
