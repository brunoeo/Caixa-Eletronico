package com.br.caixaEletronico.caixaEletronico.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("adm")
public class ADMController {


    @RequestMapping("home")
    public String home(){


        return "adm/home";
    }

    @RequestMapping("login")
    public String login(){

        return "adm/login";
    }

}
