package com.br.caixaEletronico.caixaEletronico.dto;

import com.br.caixaEletronico.caixaEletronico.domain.User;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RequisicaoNovoCliente {

    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String telefone;
    @NotBlank
    private String endereco;
    @NotBlank
    @CPF(message = "CPF inválido")
    private String cpf;
    @NotBlank
    @Size(min = 16, max = 16)
    private String numCartao;
    @NotBlank
    private String senha;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumCartao() {
        return numCartao;
    }

    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public User toUser() {
        User user = new User();
        user.setUserName(this.nome);
        user.setSenha(new BCryptPasswordEncoder().encode(this.senha));
        user.setNumCartao(this.numCartao);
        user.setCpf(this.cpf);
        user.setTelefone(this.telefone);
        user.setEndereco(this.endereco);
        user.setSaldo(0.00);
        return user;
    }

    public void toRequisicao(User user) {
        this.nome = user.getUserName();
        this.senha = user.getSenha();
        this.numCartao = user.getNumCartao();
        this.cpf = user.getCpf();
        this.telefone = user.getTelefone();
        this.endereco = user.getEndereco();
        this.id = user.getId();
    }

    public void atualizaUser(User user){
        user.setUserName(this.nome);
        user.setSenha(new BCryptPasswordEncoder().encode(this.senha));
        user.setNumCartao(this.numCartao);
        user.setCpf(this.cpf);
        user.setTelefone(this.telefone);
        user.setEndereco(this.endereco);
    }
}
