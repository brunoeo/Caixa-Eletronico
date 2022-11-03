package com.br.caixaEletronico.caixaEletronico.dto.requisicoes;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class RequisicaoSaque {

    @NotBlank
    private String numCartao;
    @NotNull
    private BigDecimal saque;

    public String getNumCartao() {
        return numCartao;
    }

    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
    }

    public BigDecimal getSaque() {
        return saque;
    }

    public void setSaque(BigDecimal saque) {
        this.saque = saque;
    }

}
