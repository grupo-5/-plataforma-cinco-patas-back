package br.com.cincopatas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cincopatas.dto.DepoimentoDTO;
import br.com.cincopatas.openapi.DepoimentoOpenAPI;
import br.com.cincopatas.request.DepoimentoRequest;
import br.com.cincopatas.service.DepoimentoService;

@CrossOrigin
@RestController
@RequestMapping("/depoimento")

public class DepoimentoController implements DepoimentoOpenAPI{

	@Autowired
	private DepoimentoService service;

	@GetMapping
	public List<DepoimentoDTO> listar() {
		return service.listar();
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody DepoimentoRequest depoimentoRequest) {
		try {
			DepoimentoDTO depoimento = service.salvar(depoimentoRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(depoimento);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

}
