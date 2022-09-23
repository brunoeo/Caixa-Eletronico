package com.br.caixaEletronico.caixaEletronico.Security;

import com.br.caixaEletronico.caixaEletronico.domain.Perfil;
import com.br.caixaEletronico.caixaEletronico.domain.User;
import com.br.caixaEletronico.caixaEletronico.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoADMService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String codigo) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByCodigo(codigo);
        if (user.isPresent()){
            for (Perfil perfil : user.get().getPerfis()){
                if (perfil.getNome().equalsIgnoreCase("ADM")){
                    return user.get();
                }else {
                    throw new UsernameNotFoundException("Acesso negado");
                }
            }
        }
        throw new UsernameNotFoundException("Dados Invalidos");
    }
}
