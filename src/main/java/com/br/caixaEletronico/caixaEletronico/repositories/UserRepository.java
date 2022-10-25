package com.br.caixaEletronico.caixaEletronico.repositories;

import com.br.caixaEletronico.caixaEletronico.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByOrderByUserNameAsc();
    Optional<User> findByCodigo(String codigo);
    Optional<User> findByNumCartao(String numCartao);

    List<User> findByUserName(String alberto);
}
