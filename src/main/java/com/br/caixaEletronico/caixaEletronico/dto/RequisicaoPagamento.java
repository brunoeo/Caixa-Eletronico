package com.br.caixaEletronico.caixaEletronico.dto;

import com.br.caixaEletronico.caixaEletronico.domain.TipoTransacao;
import com.br.caixaEletronico.caixaEletronico.domain.Transacao;
import com.br.caixaEletronico.caixaEletronico.domain.User;
import org.springframework.validation.BindingResult;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RequisicaoPagamento {
    @NotBlank
    @Pattern(regexp = "^\\d+(\\.\\d{2})?$", message = "Formato inválido")
    private String valor;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
