package com.br.caixaEletronico.caixaEletronico.dto.requisicoes;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class RequisicaoDeposito {

    @NotBlank
    private String numCartao;
    @NotNull
    private BigDecimal deposito;

    public String getNumCartao() {
        return numCartao;
    }

    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
    }

    public BigDecimal getDeposito() {
        return deposito;
    }

    public void setDeposito(BigDecimal deposito) {
        this.deposito = deposito;
    }


}
