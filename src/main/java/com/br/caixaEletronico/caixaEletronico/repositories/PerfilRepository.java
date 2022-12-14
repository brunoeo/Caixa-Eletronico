package com.br.caixaEletronico.caixaEletronico.repositories;

import com.br.caixaEletronico.caixaEletronico.domain.entities.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Perfil findByNome(String nome);

}
