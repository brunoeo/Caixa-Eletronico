package com.br.caixaEletronico.caixaEletronico.repositories;

import com.br.caixaEletronico.caixaEletronico.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
