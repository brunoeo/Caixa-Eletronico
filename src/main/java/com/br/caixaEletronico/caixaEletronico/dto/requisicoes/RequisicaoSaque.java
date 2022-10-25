package com.br.caixaEletronico.caixaEletronico.dto.requisicoes;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RequisicaoSaque {

    @NotBlank
    private String numCartao;
    @Pattern(regexp = "^\\d+(\\.\\d{2})?$", message = "Formato inválido")
    private String saque;

    public String getNumCartao() {
        return numCartao;
    }

    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
    }

    public String getSaque() {
        return saque;
    }

    public void setSaque(String saque) {
        this.saque = saque;
    }

}
