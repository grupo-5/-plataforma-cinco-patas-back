package br.com.cincopatas.dto;

import java.time.OffsetDateTime;

import br.com.cincopatas.model.Animal;
import br.com.cincopatas.model.Pessoa;
import lombok.Data;

@Data
public class SolicitacaoDTO {

	private Long id;
	private String situacao;
	private String tipoSolicitacao;
	private String justificativa;
	private OffsetDateTime data;
	private Animal animal;
	private Pessoa pessoa;
}
