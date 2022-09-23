package com.br.caixaEletronico.caixaEletronico.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cliente")
@PreAuthorize("hasAuthority('CLIENTE')")
public class ClienteController {


    @RequestMapping("home")
    public String home(){
        return "cliente/home";
    }

    @RequestMapping("login")
    public String login(){

        return "cliente/login";
    }

}
