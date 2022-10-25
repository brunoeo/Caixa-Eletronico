package com.br.caixaEletronico.caixaEletronico.dto.requisicoes;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RequisicaoTransferencia {
    @NotBlank
    private String numCartao;
    @Pattern(regexp = "^\\d+(\\.\\d{2})?$", message = "Formato inválido")
    private String valor;

    public String getNumCartao() {
        return numCartao;
    }

    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
