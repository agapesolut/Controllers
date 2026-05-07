package br.com.empresa.faturamento.cliente.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 18)
    private String cnpj;

    @Column(name = "regime_tributario", nullable = false, length = 80)
    private String regimeTributario;

    @Column(name = "honorario_atual", nullable = false, precision = 14, scale = 2)
    private BigDecimal honorarioAtual;

    @Column(name = "faixa_atual_id", nullable = false)
    private Long faixaAtualId;

    @Column(name = "responsavel_interno", nullable = false, length = 150)
    private String responsavelInterno;

    @Column(nullable = false)
    private Boolean ativo;

    protected Cliente() {
    }

    public Cliente(
        Long id,
        String nome,
        String cnpj,
        String regimeTributario,
        BigDecimal honorarioAtual,
        Long faixaAtualId,
        String responsavelInterno,
        Boolean ativo
    ) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.regimeTributario = regimeTributario;
        this.honorarioAtual = honorarioAtual;
        this.faixaAtualId = faixaAtualId;
        this.responsavelInterno = responsavelInterno;
        this.ativo = ativo;
    }

    public void atualizar(
        String nome,
        String cnpj,
        String regimeTributario,
        BigDecimal honorarioAtual,
        Long faixaAtualId,
        String responsavelInterno,
        Boolean ativo
    ) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.regimeTributario = regimeTributario;
        this.honorarioAtual = honorarioAtual;
        this.faixaAtualId = faixaAtualId;
        this.responsavelInterno = responsavelInterno;
        this.ativo = ativo;
    }

    public void desativar() {
        this.ativo = false;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getRegimeTributario() {
        return regimeTributario;
    }

    public BigDecimal getHonorarioAtual() {
        return honorarioAtual;
    }

    public Long getFaixaAtualId() {
        return faixaAtualId;
    }

    public String getResponsavelInterno() {
        return responsavelInterno;
    }

    public Boolean getAtivo() {
        return ativo;
    }
}
