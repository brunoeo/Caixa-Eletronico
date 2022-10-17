package com.br.caixaEletronico.caixaEletronico.dto.services.Cliente;

import com.br.caixaEletronico.caixaEletronico.domain.TipoTransacao;
import com.br.caixaEletronico.caixaEletronico.domain.Transacao;
import com.br.caixaEletronico.caixaEletronico.domain.User;
import com.br.caixaEletronico.caixaEletronico.dto.*;
import com.br.caixaEletronico.caixaEletronico.repositories.TransacaoRepository;
import com.br.caixaEletronico.caixaEletronico.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Transactional
    public void atualiza(RequisicaoNovoCliente requisicao, UserRepository userRepository, Authentication auth) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        requisicao.atualizaUser(user);

        userRepository.save(user);
    }
    @Transactional
    public void deposita(RequisicaoDeposito requisicaoDeposito, Authentication auth, User user,
                         TransacaoRepository transacaoRepository, UserRepository userRepository) {

        Transacao transacao = realizaTransacao(user, requisicaoDeposito);
        transacaoRepository.save(transacao);
        userRepository.save(user);

    }
    @Transactional
    public Transacao realizaTransacao(User user, RequisicaoDeposito requisicaoDeposito){
        Transacao transacao = new Transacao();
        transacao.setTipoTransacao(TipoTransacao.DEPOSITO);
        transacao.setValor(new BigDecimal(requisicaoDeposito.getDeposito()));
        transacao.setDate(LocalDate.now());
        transacao.setUser(user);
        user.setSaldo(new BigDecimal(requisicaoDeposito.getDeposito()).add(user.getSaldo()));
        return transacao;
    }
    @Transactional
    public void realizaTransacao(User user, RequisicaoSaque requisicaoSaque,
                                 TransacaoRepository transacaoRepository, UserRepository userRepository){

        Transacao transacao = new Transacao();
        transacao.setTipoTransacao(TipoTransacao.SAQUE);
        BigDecimal valorSaque = new BigDecimal(requisicaoSaque.getSaque()).multiply(BigDecimal.valueOf(-1));
        transacao.setValor(valorSaque);
        transacao.setDate(LocalDate.now());
        transacao.setUser(user);
        user.setSaldo(valorSaque.add(user.getSaldo()));
        transacaoRepository.save(transacao);
        userRepository.save(user);

    }
    @Transactional
    public void realizaTransacao(User user, RequisicaoPagamento requisicaoPagamento,
                                 TransacaoRepository transacaoRepository, UserRepository userRepository){

        Transacao transacao = new Transacao();
        transacao.setTipoTransacao(TipoTransacao.SAQUE);
        BigDecimal valorSaque = new BigDecimal(requisicaoPagamento.getValor()).multiply(BigDecimal.valueOf(-1));
        transacao.setValor(valorSaque);
        transacao.setDate(LocalDate.now());
        transacao.setUser(user);
        user.setSaldo(valorSaque.add(user.getSaldo()));
        transacaoRepository.save(transacao);
        userRepository.save(user);

    }

    public void validaSaldo(BindingResult result, User user, String valor) {

        if (new BigDecimal(valor).compareTo(user.getSaldo()) > 0){
            result.rejectValue("saque", "requisicaoSaque", "Saldo Insuficiente");
        }
    }

    public void validaNumCartao(User user, @Valid RequisicaoTransferencia requisicaoTransferencia, BindingResult result, UserRepository userRepository, List<User> users) {
        if (user.getNumCartao().equalsIgnoreCase(requisicaoTransferencia.getNumCartao())) {
            result.rejectValue("numCartao", "requisicaoSaque", "Digite outro número de cartão");
        }
        Optional<User> user1 = userRepository.findByNumCartao(requisicaoTransferencia.getNumCartao());
        if (user1.isPresent()){
            users.add(user1.get());
        }else{
            result.rejectValue("numCartao", "requisicaoSaque", "Conta não localizada");
        }
    }

    @Transactional
    public void realizaTransacao(List<User> users, RequisicaoTransferencia requisicaoTransferencia,
                                 TransacaoRepository transacaoRepository, UserRepository userRepository) {

        User userEnvia = users.get(0);
        User userRecebe = users.get(1);
        List<Transacao> transacao = new ArrayList<>();

        Transacao transacaoEnvia = new Transacao();
        transacaoEnvia.setTipoTransacao(TipoTransacao.TRANSFERENCIA);
        BigDecimal valorSaque = new BigDecimal(requisicaoTransferencia.getValor()).multiply(BigDecimal.valueOf(-1));
        transacaoEnvia.setValor(valorSaque);
        transacaoEnvia.setDate(LocalDate.now());
        transacaoEnvia.setUser(userEnvia);
        userEnvia.setSaldo(valorSaque.add(userEnvia.getSaldo()));

        Transacao transacaoRecebe = new Transacao();
        transacaoRecebe.setTipoTransacao(TipoTransacao.TRANSFERENCIA);
        transacaoRecebe.setValor(new BigDecimal(requisicaoTransferencia.getValor()));
        transacaoRecebe.setDate(LocalDate.now());
        transacaoRecebe.setUser(userRecebe);
        userRecebe.setSaldo(new BigDecimal(requisicaoTransferencia.getValor()).add(userRecebe.getSaldo()));

        transacao.add(transacaoEnvia);
        transacao.add(transacaoRecebe);

        transacaoRepository.saveAll(transacao);
        userRepository.saveAll(users);
    }
}
