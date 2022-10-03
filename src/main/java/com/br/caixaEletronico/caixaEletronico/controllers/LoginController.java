package com.br.caixaEletronico.caixaEletronico.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping()
    @RequestMapping("/loginCliente")
    public String loginCliente(){
        return "loginCliente";
    }
    @GetMapping()
    @RequestMapping("/loginADM")
    public String loginADM(){
        return "loginADM";
    }


}
