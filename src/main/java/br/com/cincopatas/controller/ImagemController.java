package br.com.cincopatas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cincopatas.dto.ImagemDTO;
import br.com.cincopatas.openapi.ImagemOpenAPI;
import br.com.cincopatas.request.ImagemRequest;
import br.com.cincopatas.service.ImagemService;

@CrossOrigin
@RestController
@RequestMapping("/imagem")
public class ImagemController implements ImagemOpenAPI{

	@Autowired
	private ImagemService service;

	@PostMapping
	public ImagemDTO salvarFoto(ImagemRequest imagem) {
		
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
