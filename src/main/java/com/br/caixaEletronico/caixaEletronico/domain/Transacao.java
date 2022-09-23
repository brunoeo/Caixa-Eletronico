package com.br.caixaEletronico.caixaEletronico.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long valor;
    @Basic
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipoTransacao;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;


    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Transacao() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
