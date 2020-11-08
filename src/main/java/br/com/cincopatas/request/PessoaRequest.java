package br.com.cincopatas.request;

import br.com.cincopatas.enums.Sexo;
import br.com.cincopatas.enums.TipoPessoa;
import br.com.cincopatas.model.Endereco;
import lombok.Data;

@Data
public class PessoaRequest {
	
	private Long id;
	private TipoPessoa tipo;
	private String nome;
	private String dataNasc;
	private Sexo sexo;
	private String cpf;
	private String rg;
	private String email;
	private String contato;
	private Endereco endereco;
	
}
