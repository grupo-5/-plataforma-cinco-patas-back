package br.com.cincopatas.dto;

import br.com.cincopatas.enums.Sexo;
import br.com.cincopatas.enums.TipoPessoa;
import br.com.cincopatas.model.Endereco;
import lombok.Data;

@Data
public class PessoaDTO {
	
	private Long id;
	private TipoPessoa tipo;
	private String nome;
	private Sexo sexo;
	private String cpf;
	private String rg;
	private String email;
	private String telefone;
	private Endereco endereco;
	
}
