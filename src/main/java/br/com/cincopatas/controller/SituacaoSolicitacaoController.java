package br.com.cincopatas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cincopatas.dto.SituacaoSolicitacaoDTO;
import br.com.cincopatas.openapi.SituacaoSolicitacaoOpenAPI;
import br.com.cincopatas.request.SituacaoSolicitacaoRequest;
import br.com.cincopatas.service.SituacaoSolicitacaoService;

@CrossOrigin
@RestController
@RequestMapping("/situacao/solicitacao")
public class SituacaoSolicitacaoController implements SituacaoSolicitacaoOpenAPI{

	@Autowired
	private SituacaoSolicitacaoService situacaoSolicitacaoService;
	
	@GetMapping
	public List<SituacaoSolicitacaoDTO> listar() {
		return situacaoSolicitacaoService.listar();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SituacaoSolicitacaoDTO> buscar(@PathVariable Long id) {
		SituacaoSolicitacaoDTO situacaoSolicitacao = situacaoSolicitacaoService.buscar(id);

		if (situacaoSolicitacao != null) {
			return ResponseEntity.ok().body(situacaoSolicitacao);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(value = "/{id}/recebidas")
	public boolean solicitacoesRecebidas(@PathVariable Long id) {
		return situacaoSolicitacaoService.solicitacoesRecebidas(id);
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody SituacaoSolicitacaoRequest situacaoSolicitacaoRequest) {
		try {
			SituacaoSolicitacaoDTO situcaoSolicitacao = situacaoSolicitacaoService.salvar(situacaoSolicitacaoRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(situcaoSolicitacao);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	
//	@PutMapping("/{id}")
//	public ResponseEntity<?> atualizar(@RequestBody Solicitacao solicitacao, @PathVariable Long id) {
//		Solicitacao solicitacaoAtual = solicitacaoService.buscar(id).orElse(null);
//
//		if (solicitacaoAtual != null) {
//			BeanUtils.copyProperties(solicitacao, solicitacaoAtual, "id");
//			solicitacaoService.atualizar(solicitacaoAtual);
//			return ResponseEntity.ok(solicitacaoAtual);
//		}
//		return ResponseEntity.notFound().build();
//	}
//	
//	@DeleteMapping("/{id}")
//	public void remover(@PathVariable Long id) {
//		solicitacaoService.remover(id);
//	}
}
