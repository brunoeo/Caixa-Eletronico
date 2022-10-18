package com.br.caixaEletronico.caixaEletronico.services.ADM;

import com.br.caixaEletronico.caixaEletronico.domain.Roles;
import com.br.caixaEletronico.caixaEletronico.domain.entities.Endereco;
import com.br.caixaEletronico.caixaEletronico.domain.entities.Perfil;
import com.br.caixaEletronico.caixaEletronico.domain.entities.User;
import com.br.caixaEletronico.caixaEletronico.dto.RequisicaoNovoADM;
import com.br.caixaEletronico.caixaEletronico.dto.RequisicaoNovoCliente;
import com.br.caixaEletronico.caixaEletronico.repositories.EnderecoRepository;
import com.br.caixaEletronico.caixaEletronico.repositories.PerfilRepository;
import com.br.caixaEletronico.caixaEletronico.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ADMService {
    public List<User> buscaUsuarios(String name, UserRepository userRepository) {
        List<User> users = userRepository.findAllByOrderByUserNameAsc();

        for (User user : users){
            if (user.getUserName().equalsIgnoreCase(name)){
                users.remove(user);
                break;
            }
        }
        return users;
    }

    public void updateEnable(UserRepository userRepository, Long id) {
        Optional<User> user = userRepository.findById(id);
        User userUpdate = user.get();
        userUpdate.setEnable();
        userRepository.save(userUpdate);
    }

    @Transactional
    public void saveUsuario(RequisicaoNovoCliente requisicao, PerfilRepository perfilRepository,
                            EnderecoRepository enderecoRepository, UserRepository userRepository) {
        User user = requisicao.toUser();
        Endereco endereco = requisicao.toEndereco();
        Perfil perfil = perfilRepository.findByNome(Roles.cliente);
        user.getPerfis().add(perfil);
        enderecoRepository.save(endereco);
        user.setEndereco(endereco);
        userRepository.save(user);
    }

    @Transactional
    public void saveUsuario(RequisicaoNovoADM requisicao, PerfilRepository perfilRepository,
                        EnderecoRepository enderecoRepository, UserRepository userRepository) {
        User user = requisicao.toUser();
        Endereco endereco = requisicao.toEndereco();
        Perfil perfil = perfilRepository.findByNome(Roles.adm);
        user.getPerfis().add(perfil);
        enderecoRepository.save(endereco);
        user.setEndereco(endereco);
        userRepository.save(user);
    }
}
