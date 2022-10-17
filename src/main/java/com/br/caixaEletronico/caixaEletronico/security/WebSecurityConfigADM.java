package com.br.caixaEletronico.caixaEletronico.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@Order(0)
public class WebSecurityConfigADM extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoADMService autenticacaoADMService;
    @Autowired
    private CryptPassword cryptPassword;

    public WebSecurityConfigADM(){
        super();
    }
    //Configuracoes de autorizacao
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/adm/**")
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().
                formLogin(form -> form
                        .loginPage("/adm/login")
                        .loginProcessingUrl("/adm/login")
                        .defaultSuccessUrl("/adm/home", true)
                        .permitAll())
                .logout(logout -> {logout.logoutUrl("/cliente/logout")
                        .logoutSuccessUrl("/cliente/login");
                })
        ;

    }

    //Configuracoes de autenticacao
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoADMService).passwordEncoder(cryptPassword.getBCrypt());
    }


}
