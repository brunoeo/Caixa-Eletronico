package com.br.caixaEletronico.caixaEletronico.dto;

import com.br.caixaEletronico.caixaEletronico.domain.entities.Endereco;
import com.br.caixaEletronico.caixaEletronico.domain.entities.User;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class RequisicaoNovoCliente {

    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    @Pattern(regexp = "^\\d{2} \\d{5}-\\d{4}$")
    private String telefone;
    @NotBlank
    @Pattern(regexp = "^\\d{5}-\\d{3}$")
    private String cep;
    @NotBlank
    private String num;
    @NotBlank
    private String rua;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    private String complemento;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        user.setNumCartao(this.numCartao);
        user.setCpf(this.cpf);
        user.setTelefone(this.telefone);
        user.setEnable(true);
        user.setSaldo(new BigDecimal(0));
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

    public void toRequisicao(User user) {
        this.nome = user.getUserName();
        this.senha = user.getSenha();
        this.numCartao = user.getNumCartao();
        this.cpf = user.getCpf();
        this.telefone = user.getTelefone();
        this.setRua(user.getEndereco().getRua());
        this.setBairro(user.getEndereco().getBairro());
        this.setCep(user.getEndereco().getCep());
        this.setCidade(user.getEndereco().getCidade());
        this.setComplemento(user.getEndereco().getComplemento());
        this.setNum(user.getEndereco().getNum());
        this.id = user.getId();
    }

    public void atualizaUser(User user){
        user.setUserName(this.nome);
        user.setSenha(new BCryptPasswordEncoder().encode(this.senha));
        user.setNumCartao(this.numCartao);
        user.setCpf(this.cpf);
        user.setTelefone(this.telefone);
        atualizaEndereco(user.getEndereco());
    }

    private void atualizaEndereco(Endereco endereco) {
        endereco.setRua(this.rua);
        endereco.setBairro(this.bairro);
        endereco.setCep(this.cep);
        endereco.setCidade(this.cidade);
        endereco.setComplemento(this.complemento);
        endereco.setNum(this.num);
    }
}
