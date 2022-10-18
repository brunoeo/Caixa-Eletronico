package com.br.caixaEletronico.caixaEletronico.domain;

import com.br.caixaEletronico.caixaEletronico.domain.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AutenticacaoHelper {

    public static User getUsuarioAutenticado(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }

}
