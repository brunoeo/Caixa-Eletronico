package com.br.caixaEletronico.caixaEletronico.security;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Profile({""})
@Configuration
public class BeanConfig {

    @Bean("passwordEncoder")
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean("modelMapper")
    public ModelMapper getModelMapper(){return new ModelMapper();}

}
