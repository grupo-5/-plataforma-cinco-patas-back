package br.com.cincopatas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cincopatas.dto.ImagemDTO;
import br.com.cincopatas.request.ImagemRequest;
import br.com.cincopatas.service.ImagemService;

@CrossOrigin
@RestController
@RequestMapping("/imagem")
public class ImagemController {

	@Autowired
	private ImagemService service;

	@PostMapping
	public ImagemDTO salvarFoto(@Valid ImagemRequest imagem) {
		
		return service.salvar(imagem);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ImagemDTO> excluir(@PathVariable Long id) {
		try {
			service.excluir(id);	
			return ResponseEntity.noContent().build();
			
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
