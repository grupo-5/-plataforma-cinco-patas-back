package br.com.cincopatas.dto;

import br.com.cincopatas.model.Endereco;
import lombok.Data;

@Data
public class PessoaDTO {
	
	private Long id;
	private String tipo;
	private String nome;
	private String dataNasc;
	private String sexo;
	private String cpf;
	private String rg;
	private String email;
	private String senha;
	private String contato;
	private Endereco endereco;
	
}
