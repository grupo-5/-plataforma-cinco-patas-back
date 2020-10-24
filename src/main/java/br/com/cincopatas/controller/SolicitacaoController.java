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

import br.com.cincopatas.dto.SolicitacaoDTO;
import br.com.cincopatas.model.Solicitacao;
import br.com.cincopatas.request.SolicitacaoRequest;
import br.com.cincopatas.service.SolicitacaoService;

@CrossOrigin
@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoController {

	@Autowired
	private SolicitacaoService solicitacaoService;
	
	@GetMapping
	public List<SolicitacaoDTO> listar() {
		return solicitacaoService.listar();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Solicitacao> buscar(@PathVariable Long id) {
		Optional<Solicitacao> solicitacao = solicitacaoService.buscar(id);

		if (solicitacao.isPresent()) {
			return ResponseEntity.ok(solicitacao.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid SolicitacaoRequest solicitacaoRequest) {
		try {
			SolicitacaoDTO solicitacao = solicitacaoService.salvar(solicitacaoRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(solicitacao);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Solicitacao solicitacao, @PathVariable Long id) {
		Solicitacao solicitacaoAtual = solicitacaoService.buscar(id).orElse(null);

		if (solicitacaoAtual != null) {
			BeanUtils.copyProperties(solicitacao, solicitacaoAtual, "id");
			solicitacaoService.atualizar(solicitacaoAtual);
			return ResponseEntity.ok(solicitacaoAtual);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		solicitacaoService.remover(id);
	}
}
