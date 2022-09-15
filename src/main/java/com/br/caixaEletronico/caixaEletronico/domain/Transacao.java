package com.br.caixaEletronico.caixaEletronico.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipoTransacao;
    private Double valor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    public Transacao(){}

    public Transacao(Long id, LocalDate data, TipoTransacao tipoTransacao, Double valor, Cliente cliente) {
        this.id = id;
        this.data = data;
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
