package com.br.caixaEletronico.caixaEletronico.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfigCliente extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoClienteService autenticacaoClienteService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Configuracoes de autorizacao
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/cliente/**")
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().
                formLogin(form -> form
                        .loginPage("/cliente/login")
                        .loginProcessingUrl("/cliente/login")
                        .defaultSuccessUrl("/cliente/home", true)
                        .permitAll())
                .logout(logout -> {logout.logoutUrl("/cliente/logout")
                        .logoutSuccessUrl("/cliente/login");
                })
        ;

    }

    //Configuracoes de autenticacao
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoClienteService).passwordEncoder(passwordEncoder);
    }

}
