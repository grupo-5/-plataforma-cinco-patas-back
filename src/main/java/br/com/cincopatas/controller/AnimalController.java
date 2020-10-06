package br.com.cincopatas.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cincopatas.dto.AnimalDTO;
import br.com.cincopatas.model.Animal;
import br.com.cincopatas.request.AnimalRequest;
import br.com.cincopatas.service.AnimalService;

@CrossOrigin
@RestController
@RequestMapping("/animal")
public class AnimalController {
	
	@Autowired
	private AnimalService animalService;
	
	@GetMapping
	public List<AnimalDTO> listar() {
		return animalService.listar();
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid AnimalRequest animalRequest) {
		try {
			AnimalDTO animal = animalService.salvar(animalRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(animal);
		}catch(Exception ex){
			return  ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		animalService.remover(id);
	}
}