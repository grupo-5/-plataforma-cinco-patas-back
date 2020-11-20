package br.com.cincopatas.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.cincopatas.dto.PessoaDTO;
import br.com.cincopatas.exception.config.Problem;
import br.com.cincopatas.model.Pessoa;
import br.com.cincopatas.request.PessoaRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags="Pessoa Controller")
public interface PessoaOpenAPI {
	
	@ApiOperation(value = "Buscar todas as pessoas", httpMethod = "GET")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Pessoas encontradas", response = PessoaDTO.class) })
	List<PessoaDTO> listar();
	
	@ApiOperation("Cadastrar uma pessoa")
	@ApiResponses({ @ApiResponse(code = 201, message = "Pessoa cadastrada", response = PessoaDTO.class) })
	ResponseEntity<PessoaDTO> salvar(
			@ApiParam(name = "corpo", value = "Representação de uma nova pessoa", required = true) 
			@Valid PessoaRequest request);
	
	@ApiOperation(value = "Buscar Pessoa pelo ID", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Buscar Pessoa pelo ID", response = PessoaDTO.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "ID a ser buscado", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<PessoaDTO> buscar(@PathVariable Long id);
	
	@ApiOperation(value = "Atualizar Pessoa pelo ID", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Pessoa atualizado com sucesso.", response = PessoaDTO.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "Id a ser atualizado", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<?> atualizar(
			@ApiParam(name = "corpo", value = "Representação de uma nova pessoa", required = true)
			@Valid PessoaRequest pessoaRequest, @PathVariable Long id);
	
	@ApiOperation(value = "Excluir Pessoa pelo ID", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 204, message = "Pessoa excluída com sucesso", response = PessoaDTO.class),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
	@ApiImplicitParam(name = "id", value = "Id a ser excluído", required = true, dataType = "int", paramType = "path", example = "1")
	ResponseEntity<Pessoa> excluir(@PathVariable Long id);
	
	@ApiOperation(value = "Buscar pessoas por instituição", httpMethod = "GET")
	@ApiResponses({@ApiResponse(code = 200, message = "Mensagem", response = PessoaDTO.class)})
	public List<PessoaDTO> listarAnimaisPorInstituicao();
	
}
