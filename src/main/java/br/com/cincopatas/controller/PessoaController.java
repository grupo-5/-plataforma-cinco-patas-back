package br.com.cincopatas.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cincopatas.dto.PessoaDTO;
import br.com.cincopatas.request.PessoaRequest;
import br.com.cincopatas.service.PessoaService;

@CrossOrigin
@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaService service;
	
	@GetMapping
	public List<PessoaDTO> listar() {
		return service.listar();
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody @Valid PessoaRequest request) {
		PessoaDTO pessoa = service.salvar(request);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
