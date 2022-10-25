package com.br.caixaEletronico.caixaEletronico.dto.mapper;

import com.br.caixaEletronico.caixaEletronico.domain.entities.Endereco;
import com.br.caixaEletronico.caixaEletronico.domain.entities.User;
import com.br.caixaEletronico.caixaEletronico.dto.requisicoes.RequisicaoNovoADM;
import com.br.caixaEletronico.caixaEletronico.dto.requisicoes.RequisicaoNovoCliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ADMMapper {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;



    public User toUser(RequisicaoNovoADM requisicaoNovoADM) {
        User user = modelMapper.map(requisicaoNovoADM, User.class);
        user.setEnable(true);
        user.setSenha(passwordEncoder.encode(requisicaoNovoADM.getSenha()));
        return user;
    }

    public Endereco toEndereco(RequisicaoNovoADM requisicaoNovoADM) {
        return modelMapper.map(requisicaoNovoADM, Endereco.class);
    }

//    public User toUser(RequisicaoNovoADM requisicaoNovoADM) {
//        User user = new User();
//        user.setUserName(requisicaoNovoADM.getNome());
//        user.setSenha(new BCryptPasswordEncoder().encode(requisicaoNovoADM.getSenha()));
//        user.setCodigo(requisicaoNovoADM.getCodigo());
//        user.setCpf(requisicaoNovoADM.getCpf());
//        user.setTelefone(requisicaoNovoADM.getTelefone());
//        user.setEnable(true);
//        return user;
//    }

//    public Endereco toEndereco(RequisicaoNovoADM requisicaoNovoADM) {
//        Endereco endereco = new Endereco();
//        endereco.setRua(requisicaoNovoADM.getRua());
//        endereco.setBairro(requisicaoNovoADM.getBairro());
//        endereco.setCep(requisicaoNovoADM.getCep());
//        endereco.setCidade(requisicaoNovoADM.getCidade());
//        endereco.setComplemento(requisicaoNovoADM.getComplemento());
//        endereco.setNum(requisicaoNovoADM.getNum());
//        return endereco;
//    }

    }
