package br.com.cincopatas.request;

import lombok.Data;

@Data
public class SituacaoSolicitacaoRequest {
	private Long id;
	private String situacao;
	private SolicitacaoRequest solicitacao;
}
