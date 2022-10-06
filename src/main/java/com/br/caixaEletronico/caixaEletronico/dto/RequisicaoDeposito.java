package com.br.caixaEletronico.caixaEletronico.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


public class RequisicaoDeposito {

    @NotBlank
    private String numCartao;
    @Pattern(regexp = "^\\d+(\\.\\d{2})?$", message = "Formato inválido")
    private String deposito;

    public String getNumCartao() {
        return numCartao;
    }

    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }


}
