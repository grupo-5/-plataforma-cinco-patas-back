package br.com.cincopatas.request;

import br.com.cincopatas.model.Endereco;
import lombok.Data;

@Data
public class InstituicaoRequest {
	
	private Long id;
	private String nome;
	private String documento;
	private String tipoDeDocumento;
	private String email;
	private String contato;
	private String razaoSocial;
	private String inscricaoEstadual;
	private Long capacidade;
	private String banco;
	private String agencia;
	private String conta;
	private Endereco endereco;

}
