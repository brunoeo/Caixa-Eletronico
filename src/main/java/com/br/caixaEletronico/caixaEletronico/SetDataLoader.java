package com.br.caixaEletronico.caixaEletronico;

import com.br.caixaEletronico.caixaEletronico.domain.entities.Transacao;
import com.br.caixaEletronico.caixaEletronico.domain.entities.User;
import com.br.caixaEletronico.caixaEletronico.repositories.TransacaoRepository;
import com.br.caixaEletronico.caixaEletronico.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SetDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (!alreadySetup){
            LocalDate vencimento = LocalDate.now().minusDays(30);


            List<Transacao> transacaos = transacaoRepository.findAll();
            List<Transacao> trasacoesAntigas = transacaos.stream().filter(s -> s.getDate().isBefore(vencimento))
                    .collect(Collectors.toList());
            transacaoRepository.deleteAll(trasacoesAntigas);
            alreadySetup = true;
        }
    }
}
