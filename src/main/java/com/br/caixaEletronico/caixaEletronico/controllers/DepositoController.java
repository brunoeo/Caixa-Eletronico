package com.br.caixaEletronico.caixaEletronico.controllers;

import com.br.caixaEletronico.caixaEletronico.domain.entities.Transacao;
import com.br.caixaEletronico.caixaEletronico.domain.entities.User;
import com.br.caixaEletronico.caixaEletronico.dto.requisicoes.RequisicaoDeposito;
import com.br.caixaEletronico.caixaEletronico.repositories.TransacaoRepository;
import com.br.caixaEletronico.caixaEletronico.repositories.UserRepository;
import com.br.caixaEletronico.caixaEletronico.services.Cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class DepositoController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private ClienteService clienteService;

    @GetMapping("formulario/deposito")
    public String formularioDepositar(RequisicaoDeposito requisicaoDeposito){


        return "/formularioDeposito";
    }


    @PostMapping("deposita")
    public String deposito(@Valid RequisicaoDeposito requisicaoDeposito, BindingResult result){

        Optional<User> userOptional = userRepository.findByNumCartao(requisicaoDeposito.getNumCartao());
        User user = new User();

        if(result.hasErrors()){
            return "/formularioDeposito";
        }else if(userOptional.isPresent()){
            user = userOptional.get();
        }else {
            result.rejectValue("numCartao", "requisicaoDeposito", "Conta não localizada");
            return "/formularioDeposito";
        }


        Transacao transacao = clienteService.realizaTransacao(user, requisicaoDeposito);
        transacaoRepository.save(transacao);
        userRepository.save(user);

        return "redirect:/cliente/login";
    }

}
