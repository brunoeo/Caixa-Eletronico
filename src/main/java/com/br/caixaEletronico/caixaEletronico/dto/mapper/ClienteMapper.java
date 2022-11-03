package com.br.caixaEletronico.caixaEletronico.dto.mapper;

import com.br.caixaEletronico.caixaEletronico.domain.entities.Endereco;
import com.br.caixaEletronico.caixaEletronico.domain.entities.User;
import com.br.caixaEletronico.caixaEletronico.dto.requisicoes.RequisicaoNovoCliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ClienteMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User toUser(RequisicaoNovoCliente requisicao) {
        User user = modelMapper.map(requisicao, User.class);
        user.setSenha(passwordEncoder.encode(requisicao.getSenha()));
        user.setSaldo(BigDecimal.ZERO);
        user.setEnable(true);
        return user;
    }

    public Endereco toEndereco(RequisicaoNovoCliente requisicao) {
        return modelMapper.map(requisicao, Endereco.class);
    }

    public void toRequisicao(User user, RequisicaoNovoCliente requisicao) {
        modelMapper.map(user, requisicao);
        modelMapper.map(user.getEndereco(), requisicao);
    }
    public void atualizaUser(User user, RequisicaoNovoCliente requisicao){
        requisicao.setId(user.getId());
        this.atualizaEndereco(user.getEndereco(), requisicao);
        modelMapper.map(requisicao, user);
        user.setSenha(passwordEncoder.encode(requisicao.getSenha()));

    }

    private void atualizaEndereco(Endereco endereco, RequisicaoNovoCliente requisicao) {
        endereco.setRua(requisicao.getRua());
        endereco.setBairro(requisicao.getBairro());
        endereco.setCep(requisicao.getCep());
        endereco.setCidade(requisicao.getCidade());
        endereco.setComplemento(requisicao.getComplemento());
        endereco.setNum(requisicao.getNum());
    }

}
