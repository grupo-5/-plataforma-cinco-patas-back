package br.com.cincopatas.controller;

import java.util.List;

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
import br.com.cincopatas.openapi.InstituicaoOpenAPI;
import br.com.cincopatas.request.InstituicaoRequest;
import br.com.cincopatas.security.permissoes.PatinhasSecurity;
import br.com.cincopatas.service.InstituicaoService;

@CrossOrigin
@RestController
//@RequestMapping("/instituicao")
public class InstituicaoController implements InstituicaoOpenAPI{

	
	@Autowired
	private PatinhasSecurity patinhasSecurity;
	@Autowired
	private InstituicaoService instituicaoService;
	

	@GetMapping(value="/insti/estado/{id}")
	public List<InstituicaoDTO> findByEstado(@PathVariable Long id) {

		return instituicaoService.buscarPorEstado(id);
		
	}
	
	@GetMapping(value="/insti")
	public List<InstituicaoDTO> listar() {
		return instituicaoService.listar();
	}
	
	@GetMapping(value = "/instituicao/codigo")
	public InstituicaoDTO listarComCodigo() {
		Long tipo = patinhasSecurity.getTipo();
		Long codigo = patinhasSecurity.getCodigo();	
		return instituicaoService.buscarComCodigo(codigo);
	}
	
	@GetMapping(value = "/instituicao/{id}")
	public ResponseEntity<InstituicaoDTO> buscar(@PathVariable Long id) {
		InstituicaoDTO instituicao = instituicaoService.buscar(id);

		if (instituicao != null) {
			return ResponseEntity.ok().body(instituicao);
		}

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping(value = "/instituicao")
	public ResponseEntity<?> salvar(@RequestBody InstituicaoRequest instituicaoRequest) {
		try {
			InstituicaoDTO instituicao = instituicaoService.salvar(instituicaoRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(instituicao);
		}catch(Exception ex){
			return  ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@DeleteMapping(value = "/instituicao/{id}")
	public void remover(@PathVariable Long id) {
		instituicaoService.remover(id);
	}
	
	@PutMapping(value = "/instituicao/{id}")
	public ResponseEntity<?> atualizar(@RequestBody InstituicaoRequest instituicaoRequest, @PathVariable Long id) {
		InstituicaoDTO instituicaoAtual = instituicaoService.buscar(id);

		if (instituicaoAtual != null) {
			BeanUtils.copyProperties(instituicaoRequest, instituicaoAtual, "id");
			instituicaoService.atualizar(instituicaoRequest);
			return ResponseEntity.ok(instituicaoAtual);
		}
		return ResponseEntity.notFound().build();
	}	

	@GetMapping(value = "/insti/{id}/cidade")
	public List<InstituicaoDTO> listarInstituicoesCidade(@PathVariable Long id) {
		return instituicaoService.buscarInstituicoesCidade(id);
	}
	
}
