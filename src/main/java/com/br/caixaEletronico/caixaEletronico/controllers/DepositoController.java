package com.br.caixaEletronico.caixaEletronico.controllers;

import com.br.caixaEletronico.caixaEletronico.domain.Transacao;
import com.br.caixaEletronico.caixaEletronico.domain.User;
import com.br.caixaEletronico.caixaEletronico.dto.RequisicaoDeposito;
import com.br.caixaEletronico.caixaEletronico.repositories.TransacaoRepository;
import com.br.caixaEletronico.caixaEletronico.repositories.UserRepository;
import com.br.caixaEletronico.caixaEletronico.services.Cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class DepositoController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TransacaoRepository transacaoRepository;
    @Autowired
    ClienteService clienteService;

    @RequestMapping("formulario/deposito")
    public String formularioDepositar(RequisicaoDeposito requisicaoDeposito){


        return "/formularioDeposito";
    }


    @PostMapping("deposita")
    public String deposito(@Valid RequisicaoDeposito requisicaoDeposito, BindingResult result){


        Optional<User> userOptional = userRepository.findByNumCartao(requisicaoDeposito.getNumCartao());
        User user = new User();

        if(result.hasErrors()){
            return "cliente/formularioDeposito";
        }else if(userOptional.isPresent()){
            user = userOptional.get();
        }else {
            result.rejectValue("numCartao", "requisicaoDeposito", "Conta não localizada");
            return "cliente/formularioDeposito";
        }


        Transacao transacao = clienteService.realizaTransacao(user, requisicaoDeposito);
        transacaoRepository.save(transacao);
        userRepository.save(user);

        return "redirect:/cliente/login";
    }

}
