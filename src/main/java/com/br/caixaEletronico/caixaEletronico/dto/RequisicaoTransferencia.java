package com.br.caixaEletronico.caixaEletronico.dto;

import com.br.caixaEletronico.caixaEletronico.domain.TipoTransacao;
import com.br.caixaEletronico.caixaEletronico.domain.Transacao;
import com.br.caixaEletronico.caixaEletronico.domain.User;
import org.springframework.validation.BindingResult;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public List<Transacao> realizaPagamento(List<User> users){
        User userEnvia = users.get(0);
        User userRecebe = users.get(1);
        List<Transacao> transacao = new ArrayList<>();

        Transacao transacaoEnvia = new Transacao();
        transacaoEnvia.setTipoTransacao(TipoTransacao.TRANSFERENCIA);
        BigDecimal valorSaque = new BigDecimal(this.valor).multiply(BigDecimal.valueOf(-1));
        transacaoEnvia.setValor(valorSaque);
        transacaoEnvia.setDate(LocalDate.now());
        transacaoEnvia.setUser(userEnvia);
        userEnvia.setSaldo(valorSaque.add(userEnvia.getSaldo()));

        Transacao transacaoRecebe = new Transacao();
        transacaoRecebe.setTipoTransacao(TipoTransacao.TRANSFERENCIA);
        transacaoRecebe.setValor(new BigDecimal(this.valor));
        transacaoRecebe.setDate(LocalDate.now());
        transacaoRecebe.setUser(userRecebe);
        userRecebe.setSaldo(new BigDecimal(this.valor).add(userRecebe.getSaldo()));

        transacao.add(transacaoEnvia);
        transacao.add(transacaoRecebe);


        return transacao;
    }

    public void validaSaldo(BindingResult result, User user) {

        if (new BigDecimal(valor).compareTo(user.getSaldo()) > 0){
            result.rejectValue("saque", "requisicaoSaque", "Saldo Insuficiente");
        }
    }
}
