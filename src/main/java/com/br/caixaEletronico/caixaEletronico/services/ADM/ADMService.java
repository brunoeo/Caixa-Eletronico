package com.br.caixaEletronico.caixaEletronico.services.ADM;

import com.br.caixaEletronico.caixaEletronico.domain.Endereco;
import com.br.caixaEletronico.caixaEletronico.domain.Perfil;
import com.br.caixaEletronico.caixaEletronico.domain.User;
import com.br.caixaEletronico.caixaEletronico.dto.RequisicaoNovoADM;
import com.br.caixaEletronico.caixaEletronico.dto.RequisicaoNovoCliente;
import com.br.caixaEletronico.caixaEletronico.repositories.EnderecoRepository;
import com.br.caixaEletronico.caixaEletronico.repositories.PerfilRepository;
import com.br.caixaEletronico.caixaEletronico.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ADMService {
    public List<User> listarUsuarios(String name, UserRepository userRepository) {
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

    public void saveUsuario(RequisicaoNovoCliente requisicao, PerfilRepository perfilRepository,
                            EnderecoRepository enderecoRepository, UserRepository userRepository) {
        User user = requisicao.toUser();
        Endereco endereco = requisicao.toEndereco();
        Perfil perfil = perfilRepository.findByNome("CLIENTE");
        user.getPerfis().add(perfil);
        enderecoRepository.save(endereco);
        user.setEndereco(endereco);
        userRepository.save(user);
    }

    public void saveUsuario(RequisicaoNovoADM requisicao, PerfilRepository perfilRepository,
                        EnderecoRepository enderecoRepository, UserRepository userRepository) {
        User user = requisicao.toUser();
        Endereco endereco = requisicao.toEndereco();
        Perfil perfil = perfilRepository.findByNome("ADM");
        user.getPerfis().add(perfil);
        enderecoRepository.save(endereco);
        user.setEndereco(endereco);
        userRepository.save(user);
    }
}
