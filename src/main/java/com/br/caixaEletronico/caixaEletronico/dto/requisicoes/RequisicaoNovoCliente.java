package com.br.caixaEletronico.caixaEletronico.dto.requisicoes;

import com.br.caixaEletronico.caixaEletronico.domain.Regexp;
import com.br.caixaEletronico.caixaEletronico.domain.entities.Endereco;
import com.br.caixaEletronico.caixaEletronico.domain.entities.User;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class RequisicaoNovoCliente {

    private Long id;
    @NotBlank
    private String userName;
    @NotBlank
    @Pattern(regexp = Regexp.telefone)
    private String telefone;
    @NotBlank
    @Pattern(regexp = Regexp.cep)
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

}
