package com.gustavo.unidac.desafio;

import org.springframework.stereotype.Component;




import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;



@Component
@Transactional
public class PopulacaoInicialBanco implements CommandLineRunner {

	@Autowired(required = false)


	@Override
	public void run(String... args) throws Exception {

	
	}
}