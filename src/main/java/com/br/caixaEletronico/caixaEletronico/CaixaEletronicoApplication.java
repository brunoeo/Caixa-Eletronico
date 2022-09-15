package com.br.caixaEletronico.caixaEletronico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.br.caixaEletronico.caixaEletronico.domain")
@SpringBootApplication
public class CaixaEletronicoApplication {


	public static void main(String[] args) {
		SpringApplication.run(CaixaEletronicoApplication.class, args);
	}

}
