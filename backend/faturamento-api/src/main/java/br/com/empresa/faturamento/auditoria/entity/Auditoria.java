package br.com.empresa.faturamento.auditoria.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "auditoria")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(nullable = false, length = 100)
    private String acao;

    @Column(nullable = false, length = 100)
    private String modulo;

    @Column(nullable = false, length = 100)
    private String entidade;

    @Column(name = "entidade_id")
    private Long entidadeId;

    @Column(name = "valor_anterior", columnDefinition = "TEXT")
    private String valorAnterior;

    @Column(name = "valor_novo", columnDefinition = "TEXT")
    private String valorNovo;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(length = 45)
    private String ip;

    @Column(columnDefinition = "TEXT")
    private String contexto;

    protected Auditoria() {
    }

    public Auditoria(
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
        this.id = id;
        this.usuarioId = usuarioId;
        this.acao = acao;
        this.modulo = modulo;
        this.entidade = entidade;
        this.entidadeId = entidadeId;
        this.valorAnterior = valorAnterior;
        this.valorNovo = valorNovo;
        this.dataHora = dataHora;
        this.ip = ip;
        this.contexto = contexto;
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getAcao() {
        return acao;
    }

    public String getModulo() {
        return modulo;
    }

    public String getEntidade() {
        return entidade;
    }

    public Long getEntidadeId() {
        return entidadeId;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public String getValorNovo() {
        return valorNovo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getIp() {
        return ip;
    }

    public String getContexto() {
        return contexto;
    }
}
