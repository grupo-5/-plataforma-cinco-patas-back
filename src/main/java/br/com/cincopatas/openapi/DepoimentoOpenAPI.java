package br.com.cincopatas.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.cincopatas.dto.DepoimentoDTO;
import br.com.cincopatas.dto.PessoaDTO;
import br.com.cincopatas.exception.config.Problem;
import br.com.cincopatas.request.DepoimentoRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Depoimento Controller")
public interface DepoimentoOpenAPI {
	
	@ApiOperation(value="Listar depoimentos", httpMethod="GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Depoimentos buscados com sucesso", response = PessoaDTO.class),
		@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	List<DepoimentoDTO> listar();
	
	@ApiOperation(value="Salvar um depoimento", httpMethod="POST")
	@ApiResponses({ @ApiResponse(code = 201, message = "Depoimento salvo com Sucesso", response = PessoaDTO.class),
		@ApiResponse(code = 400, message = "Não foi possível salvar o depoimento")})
	ResponseEntity<?> salvar(@RequestBody @Valid DepoimentoRequest depoimentoRequest);
	
}
