package br.com.cincopatas.request;

import br.com.cincopatas.model.Endereco;
import lombok.Data;

@Data
public class PessoaRequest {
	
	private Long id;
	private String nome;
	private String dataNasc;
	private String sexo;
	private String tipo;
	private String cpf;
	private String rg;
	private String email;
	private String senha;
	private String contato;
	private Endereco endereco;
	
}
