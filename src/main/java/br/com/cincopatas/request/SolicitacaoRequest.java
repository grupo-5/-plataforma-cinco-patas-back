package br.com.cincopatas.request;

import br.com.cincopatas.model.Animal;
import br.com.cincopatas.model.Pessoa;
import lombok.Data;

@Data
public class SolicitacaoRequest {
	
	private Long id;
	private String situacao;
	private String tipoSolicitacao;
	private String justificativa;
	private Animal animal;
	private Pessoa pessoa;

}
