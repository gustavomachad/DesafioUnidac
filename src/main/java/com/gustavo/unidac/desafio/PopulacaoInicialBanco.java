package com.gustavo.unidac.desafio;

import org.springframework.stereotype.Component;

import com.gustavo.unidac.desafio.rh.dominio.Pessoa;
import com.gustavo.unidac.desafio.rh.dominio.PessoaRepositorio;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;



@Component
@Transactional
public class PopulacaoInicialBanco implements CommandLineRunner {

	@Autowired
	private PessoaRepositorio pessoaRepo;

	@Override
	public void run(String... args) throws Exception {

		Pessoa p1 = new Pessoa("Gustavo");
		p1.setCafe("Pão de queijo");
		Pessoa p2 = new Pessoa("Pedro");
		p2.setCafe("Café sem açucar");

		pessoaRepo.save(p1);
		pessoaRepo.save(p2);
	}
}