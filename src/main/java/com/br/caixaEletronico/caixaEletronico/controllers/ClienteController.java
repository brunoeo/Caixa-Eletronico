package com.br.caixaEletronico.caixaEletronico.controllers;

import com.br.caixaEletronico.caixaEletronico.domain.AutenticacaoHelper;
import com.br.caixaEletronico.caixaEletronico.domain.Roles;
import com.br.caixaEletronico.caixaEletronico.domain.entities.User;
import com.br.caixaEletronico.caixaEletronico.dto.mapper.ClienteMapper;
import com.br.caixaEletronico.caixaEletronico.dto.requisicoes.*;
import com.br.caixaEletronico.caixaEletronico.repositories.TransacaoRepository;
import com.br.caixaEletronico.caixaEletronico.repositories.UserRepository;
import com.br.caixaEletronico.caixaEletronico.services.Cliente.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cliente")
@PreAuthorize("hasAuthority('" + Roles.cliente + "')")
public class ClienteController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    ClienteMapper clienteMapper;


    @GetMapping("home")
    public String home(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute(user);
        return "cliente/home";
    }

    @GetMapping("login")
    public String login(){

        return "cliente/login";
    }

    @GetMapping("formulario/alteracao")
    public String formularioAlterar(Model model, Authentication auth, RequisicaoNovoCliente requisicaoNovoCliente){

        auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        clienteMapper.toRequisicao(user, requisicaoNovoCliente);
        model.addAttribute("cliente", requisicaoNovoCliente);

        return "cliente/formularioAlteracao";
    }

    @PostMapping("atualizaCliente")
    public String atualiza(@Valid RequisicaoNovoCliente requisicao, BindingResult result){
        if(result.hasErrors()){
            return "cliente/formularioAlteracao";
        }

        clienteService.atualiza(requisicao, userRepository);

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

        User user = AutenticacaoHelper.getUsuarioAutenticado();
        model.addAttribute("user", user);
        if(result.hasErrors()){
            return "cliente/formularioDeposito";
        }
        clienteService.deposita(requisicaoDeposito, user, userRepository);
        return "redirect:/cliente/home";
    }

    @GetMapping("formulario/sacar")
    public String formularioSaque(Model model, RequisicaoSaque requisicaoSaque){
        User user = AutenticacaoHelper.getUsuarioAutenticado();
        requisicaoSaque.setNumCartao(user.getNumCartao());
        model.addAttribute("user", user);

        return "cliente/formularioSaque";
    }

    @PostMapping("saca")
    public String saca(@Valid RequisicaoSaque requisicaoSaque, BindingResult result, Model model){

        User user = AutenticacaoHelper.getUsuarioAutenticado();
        model.addAttribute("user", user);
        if(result.hasErrors()){
            return "cliente/formularioSaque";
        }

        clienteService.validaSaldo(result, user, requisicaoSaque.getSaque());

        if(result.hasErrors()){
            return "cliente/formularioSaque";
        }

        clienteService.realizaTransacao(user, requisicaoSaque, userRepository);

        return "redirect:/cliente/home";
    }

    @GetMapping("formulario/pagamento")
    public String formularioPagamento(Model model, RequisicaoPagamento requisicaoPagamento){

        User user = AutenticacaoHelper.getUsuarioAutenticado();
        model.addAttribute("user", user);

        return "cliente/formularioPagamento";
    }

    @PostMapping("pagar")
    public String pagar(@Valid RequisicaoPagamento requisicaoPagamento, BindingResult result, Model model){

        User user = AutenticacaoHelper.getUsuarioAutenticado();
        model.addAttribute("user", user);

        if(result.hasErrors()){
            return "cliente/formularioPagamento";
        }
        clienteService.validaSaldo(result, user, requisicaoPagamento.getValor());

        if(result.hasErrors()){
            return "cliente/formularioPagamento";
        }

        clienteService.realizaTransacao(user, requisicaoPagamento, userRepository);


        return "redirect:/cliente/home";
    }

    @GetMapping("formulario/transferencia")
    public String formularioTransferencia(Model model, RequisicaoTransferencia requisicaoTransferencia){

        User user = AutenticacaoHelper.getUsuarioAutenticado();
        model.addAttribute("user", user);

        return "cliente/formularioTransferencia";
    }

    @PostMapping("transferir")
    public String transferir(@Valid RequisicaoTransferencia requisicaoTransferencia,
                             BindingResult result, Model model){

        User user = AutenticacaoHelper.getUsuarioAutenticado();
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
        clienteService.realizaTransacao(users, requisicaoTransferencia, userRepository);

        return "redirect:/cliente/home";
    }

    @GetMapping("extrato")
    public String extrato(Model model){
        User user = AutenticacaoHelper.getUsuarioAutenticado();
        user.setTransacoes(clienteService.buscaTransacoes(user.getId()));
        model.addAttribute("user", user);
        return "cliente/extrato";
    }

}
