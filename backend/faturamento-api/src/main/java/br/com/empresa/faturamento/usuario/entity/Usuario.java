package br.com.empresa.faturamento.usuario.entity;

import java.util.Set;

public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String senhaHash;
    private String perfil;
    private Boolean ativo;
    private Set<String> permissoes;

    protected Usuario() {
    }

    public Usuario(
        Long id,
        String nome,
        String email,
        String senhaHash,
        String perfil,
        Boolean ativo,
        Set<String> permissoes
    ) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
        this.perfil = perfil;
        this.ativo = ativo;
        this.permissoes = permissoes;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public String getPerfil() {
        return perfil;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Set<String> getPermissoes() {
        return permissoes;
    }
}
