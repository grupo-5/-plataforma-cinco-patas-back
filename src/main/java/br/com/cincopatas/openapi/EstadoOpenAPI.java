package br.com.cincopatas.openapi;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.cincopatas.dto.PessoaDTO;
import br.com.cincopatas.exception.config.Problem;
import br.com.cincopatas.model.Cidade;
import br.com.cincopatas.model.Estado;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Estado Controller")
public interface EstadoOpenAPI {
	
	@ApiOperation(value="Salvar um Estado", httpMethod="POST")
	void salvar(@RequestBody Estado estado);
	
	@ApiOperation(value="Listar estados", httpMethod="GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "O recurso foi carregado", response = PessoaDTO.class),
		@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	List<Estado> listar();
	
	@ApiOperation(value="Listar as cidades de um estado", httpMethod="GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "O recurso foi carregado", response = PessoaDTO.class),
		@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	List<Cidade> listarCidadesPorEstado(@PathVariable Long id);
	
}
