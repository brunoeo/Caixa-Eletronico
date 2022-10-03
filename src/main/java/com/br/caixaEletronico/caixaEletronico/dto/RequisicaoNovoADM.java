package com.br.caixaEletronico.caixaEletronico.dto;

import com.br.caixaEletronico.caixaEletronico.domain.Endereco;
import com.br.caixaEletronico.caixaEletronico.domain.User;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RequisicaoNovoADM {

    @NotBlank
    private String nome;
    @NotBlank
    private String telefone;
    @NotBlank
    private String cep;
    @NotBlank
    private String num;
    @NotBlank
    private String rua;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotBlank
    private String complemento;
    @NotBlank
    @CPF(message = "CPF inválido")
    private String cpf;
    @NotBlank
    private String codigo;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public User toUser() {
        User user = new User();
        user.setUserName(this.nome);
        user.setSenha(new BCryptPasswordEncoder().encode(this.senha));
        user.setCodigo(this.codigo);
        user.setCpf(this.cpf);
        user.setTelefone(this.telefone);
        user.setEnable(true);
        return user;
    }

    public Endereco toEndereco() {
        Endereco endereco = new Endereco();
        endereco.setRua(this.rua);
        endereco.setBairro(this.bairro);
        endereco.setCep(this.cep);
        endereco.setCidade(this.cidade);
        endereco.setComplemento(this.complemento);
        endereco.setNum(this.num);
        return endereco;
    }
}
