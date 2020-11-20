package br.com.cincopatas.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.cincopatas.dto.AnimalDTO;
import br.com.cincopatas.exception.config.Problem;
import br.com.cincopatas.filtro.AnimalFiltro;
import br.com.cincopatas.request.AnimalRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags="Animal Controller")
public interface AnimalOpenAPI {
	
	@ApiOperation(value="Buscar todos os animais", httpMethod="GET")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Animais encontrados", response = AnimalDTO.class) })
	List<AnimalDTO> listarAnimais();
	
	@ApiOperation(value = "Buscar animais por filtro", httpMethod = "GET")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Busca com sucesso", response = AnimalDTO.class) })
	List<AnimalDTO> listarAnimaisPorFiltro(AnimalFiltro filtro);
	
	@ApiOperation(value = "Cadastrar uma Animal", httpMethod="POST")
	@ApiResponses({ @ApiResponse(code = 201, message = "Animal cadastrada", response = AnimalDTO.class) })
	ResponseEntity<?> salvar(
			@ApiParam(name = "corpo", value = "Representação de uma nova Animal", required = true) 
			@Valid AnimalRequest request);
	
	@ApiOperation(value = "Buscar Animal pelo ID", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Buscar Animal pelo ID", response = AnimalDTO.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "ID a ser buscado", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<AnimalDTO> buscar(@PathVariable Long id);
	
	@ApiOperation(value = "Atualizar Animal pelo ID", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Animal atualizado com sucesso.", response = AnimalDTO.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "Id a ser atualizado", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<?> atualizar(
			@ApiParam(name = "corpo", value = "Representação de uma nova Animal", required = true)
			@Valid AnimalRequest AnimalRequest, @PathVariable Long id);
	
	@ApiOperation(value = "Excluir Animal pelo ID", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 204, message = "Animal excluída com sucesso", response = AnimalDTO.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "Id a ser excluído", required = true, dataType = "int", paramType = "path", example = "1")
	void remover(@PathVariable Long id);
	
	@ApiOperation(value = "Buscar animais por instituicao", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Animais encontrados", response = AnimalDTO.class),
		@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	List<AnimalDTO> listarAnimaisPorInstituicao();
	
}
