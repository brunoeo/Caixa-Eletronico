package com.br.caixaEletronico.caixaEletronico.controllers;

import com.br.caixaEletronico.caixaEletronico.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    UserRepository clienteRepository;

    @RequestMapping("home")
    public String home(){


        return "cliente/home";
    }

    @RequestMapping("login")
    public String login(){

        return "cliente/login";
    }

}
