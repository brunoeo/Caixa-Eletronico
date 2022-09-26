package com.br.caixaEletronico.caixaEletronico.controllers;

import com.br.caixaEletronico.caixaEletronico.domain.Perfil;
import com.br.caixaEletronico.caixaEletronico.domain.User;
import com.br.caixaEletronico.caixaEletronico.dto.RequisicaoNovoCliente;
import com.br.caixaEletronico.caixaEletronico.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("cliente")
@PreAuthorize("hasAuthority('CLIENTE')")
public class ClienteController {

    @Autowired
    UserRepository userRepository;


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

    @RequestMapping("formulario")
    public String formulario(Model model, Authentication auth, RequisicaoNovoCliente requisicaoNovoCliente){

        auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        requisicaoNovoCliente.toRequisicao(user);
        model.addAttribute("cliente", requisicaoNovoCliente);

        return "cliente/formularioAlteracao";
    }

    @PostMapping("atualizaCliente")
    public String atualiza(@Valid RequisicaoNovoCliente requisicao, BindingResult result){
        if(result.hasErrors()){
            return "adm/formularioCliente";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        requisicao.atualizaUser(user);

        userRepository.save(user);

        return "redirect:/cliente/home";
    }


}
