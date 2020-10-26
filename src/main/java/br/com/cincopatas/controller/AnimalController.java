package br.com.cincopatas.controller;

import java.util.List;
import java.util.Optional;

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
import br.com.cincopatas.mapper.AnimalMapper;
import br.com.cincopatas.model.Animal;
import br.com.cincopatas.request.AnimalRequest;
import br.com.cincopatas.service.AnimalService;

@CrossOrigin
@RestController
@RequestMapping("/animal")
public class AnimalController {

	@Autowired
	private AnimalService animalService;
	@Autowired
	private AnimalMapper animalMapper;

	@GetMapping
	public List<AnimalDTO> listar() {
		return animalService.listar();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Animal> buscar(@PathVariable Long id) {
		Optional<Animal> animal = animalService.buscar(id);

		if (animal.isPresent()) {
			return ResponseEntity.ok(animal.get());
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
	public ResponseEntity<?> atualizar(@RequestBody Animal animal, @PathVariable Long id) {
		Animal animalAtual = animalService.buscar(id).orElse(null);

		if (animalAtual != null) {
			BeanUtils.copyProperties(animal, animalAtual, "id");
			animalService.atualizar(animalAtual);
			return ResponseEntity.ok(animalAtual);
		}
		return ResponseEntity.notFound().build();
	}
}