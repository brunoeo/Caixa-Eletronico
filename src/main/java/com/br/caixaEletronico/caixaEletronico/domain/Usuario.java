package com.br.caixaEletronico.caixaEletronico.domain;

import javax.persistence.MappedSuperclass;
import java.awt.*;

@MappedSuperclass
public abstract class Usuario {

    protected String nomeUsuario;
    protected String senha;
    protected String cpf;
    protected String telefone;
    protected String endereco;

    public Usuario(){}

    public Usuario(String nomeUsuario, String senha, String cpf, String telefone, String endereco) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
