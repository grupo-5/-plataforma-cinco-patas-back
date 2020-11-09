package br.com.cincopatas.dto;

import java.time.OffsetDateTime;
import java.util.List;

import br.com.cincopatas.model.SituacaoSolicitacao;
import lombok.Data;

@Data
public class SolicitacaoDTO {

	private Long id;
	private String situacao;
	private String tipoSolicitacao;
	private String justificativa;
	private OffsetDateTime data;
	private AnimalDTO animal;
	private PessoaDTO pessoa;
//	@JsonIgnore	
	private List<SituacaoSolicitacao> situacoes;
}
