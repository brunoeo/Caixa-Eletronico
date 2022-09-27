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

    public Transacao realizaSaque(User user){
        Transacao transacao = new Transacao();
        transacao.setTipoTransacao(TipoTransacao.SAQUE);
        BigDecimal valorSaque = new BigDecimal(this.saque).multiply(BigDecimal.valueOf(-1));
        transacao.setValor(valorSaque);
        transacao.setDate(LocalDate.now());
        transacao.setUser(user);
        user.setSaldo(valorSaque.add(user.getSaldo()));
        return transacao;
    }

    public void validaSaldo(BindingResult result, User user) {

        if (new BigDecimal(saque).compareTo(user.getSaldo()) > 0){
            result.rejectValue("saque", "requisicaoSaque", "Saldo Insuficiente");
        }
    }
}
