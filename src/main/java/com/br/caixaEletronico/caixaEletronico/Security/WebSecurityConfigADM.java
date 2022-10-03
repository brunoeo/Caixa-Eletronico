package com.br.caixaEletronico.caixaEletronico.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@Order(0)
public class WebSecurityConfigADM extends WebSecurityConfigurerAdapter {

    @Autowired
    AutenticacaoADMService autenticacaoADMService;

    public WebSecurityConfigADM(){
        super();
    }
    //Configuracoes de autorizacao
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/adm/**")
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
                .csrf().disable()
        ;

    }

    //Configuracoes de autenticacao
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoADMService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
