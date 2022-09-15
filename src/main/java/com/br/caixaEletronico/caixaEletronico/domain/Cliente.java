package com.br.caixaEletronico.caixaEletronico.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String numeroDoCartao;
    private Double saldo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Transacao> trasacoes = new ArrayList<>();

    public Cliente(){}

    public Cliente(String nomeUsuario, String senha, String cpf, String telefone, String endereco, Long id, String numeroDoCartao, List<Transacao> trasacoes) {
        super(nomeUsuario, senha, cpf, telefone, endereco);
        this.id = id;
        this.numeroDoCartao = numeroDoCartao;
        this.saldo = 0d;
        this.trasacoes = trasacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public void setNumeroDoCartao(String numeroDoCartao) {
        this.numeroDoCartao = numeroDoCartao;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public List<Transacao> getTrasacoes() {
        return trasacoes;
    }

    public void setTrasacoes(List<Transacao> trasacoes) {
        this.trasacoes = trasacoes;
    }
}
