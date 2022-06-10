package com.gustavo.unidac.desafio.rh.service;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gustavo.unidac.desafio.rh.dominio.Pessoa;
import com.gustavo.unidac.desafio.rh.dominio.PessoaRepositorio;

@Service
@Transactional
@Controller
public class PessoaControle {

	private PessoaRepositorio pessoaRepo;

	public PessoaControle(PessoaRepositorio pessoaRepo) {
		this.pessoaRepo = pessoaRepo;
	}
	
	@GetMapping("rh/pessoas/nova")
	public String novaPessoa(Model model) {
		
		model.addAttribute("pessoa", new Pessoa(""));
		
		return "rh/pessoas/form";
	}
	
	@GetMapping("rh/pessoas/{id}")
	public String alterarPessoa(@PathVariable("id") long id, Model model) {
		Optional<Pessoa> pessoaOpt = pessoaRepo.findById(id);
		if (pessoaOpt.isPresent()) {
			throw new IllegalArgumentException("Pessoa inválida.");
		}
		
		model.addAttribute("pessoa", pessoaOpt.get());
		
		return "rh/pessoas/form";
	}
	
	@GetMapping("rh/pessoas")
	public String pessoas(Model model) {
		model.addAttribute("listaPessoas", pessoaRepo.findAll());
		return "index";
	}
	
	@PostMapping("rh/pessoas/salvar")
	public String salvarPessoa(@Valid @ModelAttribute("pessoa") Pessoa pessoa, BindingResult BindingResult) {
		if(BindingResult.hasErrors()) {
			return"rh/pessoas/form";
		}
		
		pessoaRepo.save(pessoa);
		return "redirect:/rh/pessoas";
	}

	@GetMapping("/rh/pessoas/excluir/{id}")
	public String excluirPessoa(@PathVariable("id") long id) {
		Optional<Pessoa> pessoaOpt = pessoaRepo.findById(id);
		if (pessoaOpt.isEmpty()) {
			throw new IllegalArgumentException("Pessoa inválida.");
		}
		
		pessoaRepo.delete(pessoaOpt.get());
		return "redirect:/rh/pessoas";
	}

	
	
}

