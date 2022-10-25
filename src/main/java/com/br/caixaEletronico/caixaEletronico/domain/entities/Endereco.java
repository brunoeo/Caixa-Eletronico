package com.br.caixaEletronico.caixaEletronico.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Pattern(regexp = "^\\d{5}-\\d{3}$")
    @Column(nullable = false, length = 9)
    private String cep;
    @NotNull
    @Column(nullable = false, length = 5)
    private String num;
    @NotNull
    @Column(nullable = false, length = 50)
    private String rua;
    @NotNull
    @Column(nullable = false, length = 50)
    private String bairro;
    @NotNull
    @Column(nullable = false, length = 50)
    private String cidade;
    @NotNull
    @Column(nullable = false, length = 100)
    private String complemento;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
