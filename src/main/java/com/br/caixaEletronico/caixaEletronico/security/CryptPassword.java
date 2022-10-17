package com.br.caixaEletronico.caixaEletronico.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CryptPassword {

    @Bean
    public BCryptPasswordEncoder getBCrypt(){
        return new BCryptPasswordEncoder();
    }

}
