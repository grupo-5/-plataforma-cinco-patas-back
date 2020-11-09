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

import br.com.cincopatas.dto.InstituicaoDTO;
import br.com.cincopatas.model.Animal;
import br.com.cincopatas.model.Instituicao;
import br.com.cincopatas.request.InstituicaoRequest;
import br.com.cincopatas.service.InstituicaoService;



@CrossOrigin
@RestController
@RequestMapping("/instituicao")
public class InstituicaoController {
	
	@Autowired
	private InstituicaoService instituicaoService;
	
	@GetMapping
	public List<InstituicaoDTO> listar() {
		return instituicaoService.listar();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Instituicao> buscar(@PathVariable Long id) {
		Optional<Instituicao> instituicao = instituicaoService.buscar(id);

		if (instituicao.isPresent()) {
			return ResponseEntity.ok(instituicao.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid InstituicaoRequest instituicaoRequest) {
		try {
			InstituicaoDTO instituicao = instituicaoService.salvar(instituicaoRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(instituicao);
		}catch(Exception ex){
			return  ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		instituicaoService.remover(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Instituicao instituicao, @PathVariable Long id) {
		Instituicao instituicaoAtual = instituicaoService.buscar(id).orElse(null);

		if (instituicaoAtual != null) {
			BeanUtils.copyProperties(instituicao, instituicaoAtual, "id");
			instituicaoService.atualizar(instituicaoAtual);
			return ResponseEntity.ok(instituicaoAtual);
		}
		return ResponseEntity.notFound().build();
	}


	
	
	

}
