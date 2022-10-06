package com.br.caixaEletronico.caixaEletronico.dto;


import com.br.caixaEletronico.caixaEletronico.domain.TipoTransacao;
import com.br.caixaEletronico.caixaEletronico.domain.Transacao;
import com.br.caixaEletronico.caixaEletronico.domain.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

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
