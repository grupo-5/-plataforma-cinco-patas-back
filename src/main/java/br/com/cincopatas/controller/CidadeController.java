package br.com.cincopatas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cincopatas.model.Cidade;
import br.com.cincopatas.service.CidadeService;

@CrossOrigin
@RestController
@RequestMapping("/cidade")
public class CidadeController{
	
	@Autowired
	private CidadeService service;
	
	@PostMapping
	public void salvar(@RequestBody Cidade cidade) {
		service.salvar(cidade);
	}
	
	@GetMapping
	public List<Cidade> listar(){
		return service.listar();
	}
	
}
