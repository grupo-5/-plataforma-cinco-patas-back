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

import br.com.cincopatas.dto.InstituicaoDTO;
import br.com.cincopatas.request.InstituicaoRequest;
import br.com.cincopatas.service.InstituicaoService;



@CrossOrigin
@RestController
@RequestMapping("/instituicao")
public class InstituicaoController {
	
	@Autowired
	private InstituicaoService instituicaoService;
	

	@GetMapping(value="/estado/{id}")
	public List<InstituicaoDTO> findByEstado(@PathVariable Long id) {

		return instituicaoService.buscarPorEstado(id);
		
	}
	
	@GetMapping
	public List<InstituicaoDTO> listar() {
		return instituicaoService.listar();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<InstituicaoDTO> buscar(@PathVariable Long id) {
		InstituicaoDTO instituicao = instituicaoService.buscar(id);

		if (instituicao != null) {
			return ResponseEntity.ok().body(instituicao);
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
	public ResponseEntity<?> atualizar(@RequestBody InstituicaoRequest instituicaoRequest, @PathVariable Long id) {
		InstituicaoDTO instituicaoAtual = instituicaoService.buscar(id);

		if (instituicaoAtual != null) {
			BeanUtils.copyProperties(instituicaoRequest, instituicaoAtual, "id");
			instituicaoService.atualizar(instituicaoRequest);
			return ResponseEntity.ok(instituicaoAtual);
		}
		return ResponseEntity.notFound().build();
	}	

	@GetMapping(value = "/{id}/cidade")
	public List<InstituicaoDTO> listarInstituicoesCidade(@PathVariable Long id) {
		return instituicaoService.buscarInstituicoesCidade(id);
	}
	
}
