package br.com.cincopatas.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.cincopatas.dto.InstituicaoDTO;
import br.com.cincopatas.exception.config.Problem;
import br.com.cincopatas.request.InstituicaoRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags="Instituicao Controller")
public interface InstituicaoOpenAPI {
	
	@ApiOperation(value = "Buscar todas as instituições", httpMethod = "GET")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Buscar todas as Instituicaos", response = InstituicaoDTO.class) })
	List<InstituicaoDTO> listar();
	
	@ApiOperation("Cadastrar uma Instituicao")
	@ApiResponses({ @ApiResponse(code = 201, message = "Instituicao cadastrada", response = InstituicaoDTO.class) })
	ResponseEntity<?> salvar(
			@ApiParam(name = "corpo", value = "Representação de uma nova Instituicao", required = true) 
			@Valid InstituicaoRequest request);
	
	@ApiOperation(value = "Buscar Instituicao pelo ID", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Buscar Instituicao pelo ID", response = InstituicaoDTO.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "ID a ser buscado", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<InstituicaoDTO> buscar(@PathVariable Long id);
	
	@ApiOperation(value = "Atualizar Instituicao pelo ID", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Instituicao atualizado com sucesso.", response = InstituicaoDTO.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "Id a ser atualizado", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<?> atualizar(
			@ApiParam(name = "corpo", value = "Representação de uma nova Instituicao", required = true)
			@Valid InstituicaoRequest InstituicaoRequest, @PathVariable Long id);
	
	@ApiOperation(value = "Excluir Instituicao pelo ID", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 204, message = "Instituicao excluída com sucesso", response = InstituicaoDTO.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "Id a ser excluído", required = true, dataType = "int", paramType = "path", example = "1")
	void remover(@PathVariable Long id);
	
	@ApiOperation(value="Buscar instituicoes de uma cidade", httpMethod="GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Busca instituicoes pelo ID da cidade", response = InstituicaoDTO.class),
		@ApiResponse(code = 404, message = "Não foram encontradas instituições", response = Problem.class) })
	List<InstituicaoDTO> listarInstituicoesCidade(Long Id);
	
	@ApiOperation(value="Buscar instituicoes de um estado", httpMethod="GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Busca instituicoes pelo ID do estado", response = InstituicaoDTO.class),
		@ApiResponse(code = 404, message = "Não foram encontradas instituições", response = Problem.class) })
	List<InstituicaoDTO> findByEstado(Long Id);
	
}
