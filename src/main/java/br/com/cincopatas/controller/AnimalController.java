package br.com.cincopatas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cincopatas.dto.AnimalDTO;
import br.com.cincopatas.filtro.AnimalFiltro;
import br.com.cincopatas.request.AnimalRequest;
import br.com.cincopatas.security.permissoes.PatinhasSecurity;
import br.com.cincopatas.service.AnimalService;

@CrossOrigin
@RestController
@RequestMapping("/animal")
public class AnimalController {

	@Autowired
	private AnimalService animalService;

	@Autowired
	private PatinhasSecurity patinhasSecurity;

	@GetMapping
	public List<AnimalDTO> listarAnimaisPorFiltro(AnimalFiltro filtro) {	
		return animalService.listar(filtro);
	}
	
	@GetMapping("/instituicao")
	public List<AnimalDTO> listarAnimaisPorInstituicao() {

		Long codigo = patinhasSecurity.getCodigo();	
		
		return animalService.listarPorInstituicao(codigo);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AnimalDTO> buscar(@PathVariable Long id) {
		AnimalDTO animal = animalService.buscar(id);

		if (animal != null) {
			return ResponseEntity.ok().body(animal);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid AnimalRequest animalRequest) {
		try {
			AnimalDTO animal = animalService.salvar(animalRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(animal);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		animalService.remover(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody AnimalRequest animalRequest, @PathVariable Long id) {
		AnimalDTO animalAtual = animalService.buscar(id);
		
		if (animalAtual != null) {
			BeanUtils.copyProperties(animalRequest, animalAtual, "id");
			animalService.atualizar(animalRequest);
			return ResponseEntity.ok(animalAtual);
		}
		return ResponseEntity.notFound().build();
	}

}

