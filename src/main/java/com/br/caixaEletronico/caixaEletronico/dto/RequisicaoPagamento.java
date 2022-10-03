package com.br.caixaEletronico.caixaEletronico.dto;

import com.br.caixaEletronico.caixaEletronico.domain.TipoTransacao;
import com.br.caixaEletronico.caixaEletronico.domain.Transacao;
import com.br.caixaEletronico.caixaEletronico.domain.User;
import org.springframework.validation.BindingResult;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RequisicaoPagamento {
    @Pattern(regexp = "^\\d+(\\.\\d{2})?$", message = "Formato inválido")
    private String valor;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Transacao realizaPagamento(User user){
        Transacao transacao = new Transacao();
        transacao.setTipoTransacao(TipoTransacao.PAGAMENTO);
        BigDecimal valorSaque = new BigDecimal(this.valor).multiply(BigDecimal.valueOf(-1));
        transacao.setValor(valorSaque);
        transacao.setDate(LocalDate.now());
        transacao.setUser(user);
        user.setSaldo(valorSaque.add(user.getSaldo()));
        return transacao;
    }

    public void validaSaldo(BindingResult result, User user) {

        if (new BigDecimal(valor).compareTo(user.getSaldo()) > 0){
            result.rejectValue("valor", "requisicaoSaque", "Saldo Insuficiente");
        }
    }
}
