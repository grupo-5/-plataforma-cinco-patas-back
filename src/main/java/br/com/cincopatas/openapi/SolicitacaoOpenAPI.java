package br.com.cincopatas.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.cincopatas.dto.SolicitacaoDTO;
import br.com.cincopatas.request.SolicitacaoRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Solicitacao Controller")
public interface SolicitacaoOpenAPI {

	@ApiOperation(value="Listar solicitações", httpMethod="GET")
	List<SolicitacaoDTO> listar();
	
	@ApiOperation(value="Buscar solicitações por ID", httpMethod="GET")
	ResponseEntity<SolicitacaoDTO> buscar(@PathVariable Long id);
	
	@ApiOperation(value="Salvar uma solicitacao", httpMethod="POST")
	ResponseEntity<?> salvar(@RequestBody @Valid SolicitacaoRequest solicitacaoRequest);
	
	@ApiOperation(value="Salvar uma solicitacao", httpMethod="PUT")
	ResponseEntity<?> atualizar(@RequestBody SolicitacaoRequest solicitacaoRequest, @PathVariable Long id);
	
	@ApiOperation(value="Salvar uma solicitacao", httpMethod="DELETE")
	void remover(@PathVariable Long id);
	
}
