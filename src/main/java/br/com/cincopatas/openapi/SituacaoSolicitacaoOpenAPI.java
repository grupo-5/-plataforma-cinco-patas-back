package br.com.cincopatas.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.cincopatas.dto.SituacaoSolicitacaoDTO;
import br.com.cincopatas.request.SituacaoSolicitacaoRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Situacao Solicitacao Controller")
public interface SituacaoSolicitacaoOpenAPI {

	
	@ApiOperation(value="Listar solicitações", httpMethod="GET")
	List<SituacaoSolicitacaoDTO> listar();
	
	@ApiOperation(value="Listar solicitação por ID", httpMethod="GET")
	ResponseEntity<SituacaoSolicitacaoDTO> buscar(@PathVariable Long id);
	
	@ApiOperation(value="Listar situações das solicitações da instituição", httpMethod="GET")
	boolean solicitacoesRecebidas(@PathVariable Long id);
	
	@ApiOperation(value="Salvar a situação de uma solicitação", httpMethod="POST")
	ResponseEntity<?> salvar(@RequestBody @Valid SituacaoSolicitacaoRequest situacaoSolicitacaoRequest);
	
}
