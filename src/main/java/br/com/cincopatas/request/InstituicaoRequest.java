package br.com.cincopatas.request;

import br.com.cincopatas.model.Endereco;
import br.com.cincopatas.model.Imagem;
import lombok.Data;

@Data
public class InstituicaoRequest {
	
	private Long id;
	private String nome;
	private String numeroDocumento;
	private String tipoDocumento;
	private String email;
	private String contato;
	private String razaoSocial;
	private String inscricaoEstadual;
	private Long capacidade;
	private String banco;
	private String agencia;
	private String conta;
	private Endereco endereco;
	private Imagem imagem; 
}
