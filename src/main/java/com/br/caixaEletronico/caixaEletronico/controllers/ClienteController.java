package com.br.caixaEletronico.caixaEletronico.controllers;

import com.br.caixaEletronico.caixaEletronico.domain.Transacao;
import com.br.caixaEletronico.caixaEletronico.domain.User;
import com.br.caixaEletronico.caixaEletronico.dto.*;
import com.br.caixaEletronico.caixaEletronico.repositories.TransacaoRepository;
import com.br.caixaEletronico.caixaEletronico.repositories.UserRepository;
import com.br.caixaEletronico.caixaEletronico.services.Cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("cliente")
@PreAuthorize("hasAuthority('CLIENTE')")
public class ClienteController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TransacaoRepository transacaoRepository;
    @Autowired
    ClienteService clienteService;


    @RequestMapping("home")
    public String home(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute(user);
        return "cliente/home";
    }

    @RequestMapping("login")
    public String login(){

        return "cliente/login";
    }

    @RequestMapping("formulario/alteracao")
    public String formularioAlterar(Model model, Authentication auth, RequisicaoNovoCliente requisicaoNovoCliente){

        auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        requisicaoNovoCliente.toRequisicao(user);
        model.addAttribute("cliente", requisicaoNovoCliente);

        return "cliente/formularioAlteracao";
    }

    @PostMapping("atualizaCliente")
    public String atualiza(@Valid RequisicaoNovoCliente requisicao, BindingResult result, Authentication auth){
        if(result.hasErrors()){
            return "cliente/formularioAlteracao";
        }

        clienteService.atualiza(requisicao, userRepository, auth);

        return "redirect:/cliente/home";
    }


    @RequestMapping("formulario/deposito")
    public String formularioDepositar(Model model, RequisicaoDeposito requisicaoDeposito){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        requisicaoDeposito.setNumCartao(user.getNumCartao());
        model.addAttribute("user", user);

        return "cliente/formularioDeposito";
    }

    @PostMapping("deposita")
    public String deposito(@Valid RequisicaoDeposito requisicaoDeposito, BindingResult result, Model model, Authentication auth){

        auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);
        if(result.hasErrors()){
            return "cliente/formularioDeposito";
        }
        clienteService.deposita(requisicaoDeposito, auth, user, transacaoRepository, userRepository);
        return "redirect:/cliente/home";
    }

    @RequestMapping("formulario/sacar")
    public String formularioSaque(Model model, RequisicaoSaque requisicaoSaque){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        requisicaoSaque.setNumCartao(user.getNumCartao());
        model.addAttribute("user", user);

        return "cliente/formularioSaque";
    }

    @PostMapping("saca")
    public String saca(@Valid RequisicaoSaque requisicaoSaque, BindingResult result, Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);
        if(result.hasErrors()){
            return "cliente/formularioSaque";
        }

        clienteService.validaSaldo(result, user, requisicaoSaque.getSaque());

        if(result.hasErrors()){
            return "cliente/formularioSaque";
        }

        clienteService.realizaTransacao(user, requisicaoSaque, transacaoRepository, userRepository);

        return "redirect:/cliente/home";
    }

    @RequestMapping("formulario/pagamento")
    public String formularioPagamento(Model model, RequisicaoPagamento requisicaoPagamento){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        return "cliente/formularioPagamento";
    }

    @PostMapping("pagar")
    public String pagar(@Valid RequisicaoPagamento requisicaoPagamento, BindingResult result, Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);
        if(result.hasErrors()){
            return "cliente/formularioPagamento";
        }
        clienteService.validaSaldo(result, user, requisicaoPagamento.getValor());

        if(result.hasErrors()){
            return "cliente/formularioPagamento";
        }

        clienteService.realizaTransacao(user, requisicaoPagamento, transacaoRepository, userRepository);


        return "redirect:/cliente/home";
    }

    @RequestMapping("formulario/transferencia")
    public String formularioTransferencia(Model model, RequisicaoTransferencia requisicaoTransferencia){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        return "cliente/formularioTransferencia";
    }

    @PostMapping("transferir")
    public String transferir(@Valid RequisicaoTransferencia requisicaoTransferencia,
                             BindingResult result, Model model, Authentication auth){

        auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        if(result.hasErrors()){
            return "cliente/formularioTransferencia";
        }

        List<User> users = new ArrayList<>();
        users.add(user);

        clienteService.validaSaldo(result, user, requisicaoTransferencia.getValor());
        clienteService.validaNumCartao(user, requisicaoTransferencia, result, userRepository, users);

        if(result.hasErrors()){
            return "cliente/formularioTransferencia";
        }
        clienteService.realizaTransacao(users, requisicaoTransferencia, transacaoRepository, userRepository);

        return "redirect:/cliente/home";
    }

    @RequestMapping("extrato")
    public String extrato(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        user.setTransacoes(transacaoRepository.findByUserId(user.getId()));
        model.addAttribute("user", user);
        return "cliente/extrato";
    }

}
