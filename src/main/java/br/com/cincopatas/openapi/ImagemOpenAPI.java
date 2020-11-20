package br.com.cincopatas.openapi;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.cincopatas.dto.ImagemDTO;
import br.com.cincopatas.request.ImagemRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Imagem Controller")
public interface ImagemOpenAPI {

	@ApiOperation(value="Salvar uma foto", httpMethod="POST")
	@ApiResponses({
		@ApiResponse(code=201, message="O recurso foi salvo com sucesso", response=ImagemDTO.class),
		@ApiResponse(code=404, message="Não foi possível salvar o recurso")
	})
	public ImagemDTO salvarFoto(@Valid ImagemRequest imagem);
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ImagemDTO> excluir(@PathVariable Long id);
	
}
