package com.br.caixaEletronico.caixaEletronico.repositories;

import com.br.caixaEletronico.caixaEletronico.domain.ADM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ADMRepository extends JpaRepository<ADM, Long> {
}
