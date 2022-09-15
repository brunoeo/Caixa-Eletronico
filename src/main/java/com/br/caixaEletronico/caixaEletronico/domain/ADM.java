package com.br.caixaEletronico.caixaEletronico.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ADM extends Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public ADM(){
    }

    public ADM(String nomeUsuario, String senha, String cpf, String telefone, String endereco, Long id) {
        super(nomeUsuario, senha, cpf, telefone, endereco);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
