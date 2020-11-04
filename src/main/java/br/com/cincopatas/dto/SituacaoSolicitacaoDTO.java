package br.com.cincopatas.dto;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class SituacaoSolicitacaoDTO {
	private Long id;
	private String situacao;
	private OffsetDateTime data;
	private SolicitacaoDTO solicitacao;
}
