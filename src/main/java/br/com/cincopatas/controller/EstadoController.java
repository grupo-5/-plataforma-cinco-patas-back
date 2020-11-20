package br.com.cincopatas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cincopatas.model.Cidade;
import br.com.cincopatas.model.Estado;
import br.com.cincopatas.openapi.EstadoOpenAPI;
import br.com.cincopatas.service.EstadoService;



@CrossOrigin
@RestController
@RequestMapping("/estado")
public class EstadoController implements EstadoOpenAPI{
	
	@Autowired
	private EstadoService service;
	
	@PostMapping
	public void salvar(@RequestBody Estado estado) {
		service.salvar(estado);
	}
	
	@GetMapping
	public List<Estado> listar(){
		return service.listar();
	}
	
	@GetMapping("/{id}/cidades")
	public List<Cidade> listarCidadesPorEstado(@PathVariable Long id){
		return service.buscarCidades(id);
	}
}
