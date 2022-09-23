package com.br.caixaEletronico.caixaEletronico.repositories;

import com.br.caixaEletronico.caixaEletronico.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String name);
    Optional<User> findByCodigo(String codigo);
    Optional<User> findByNumCartao(String numCartao);
}
