package br.com.cincopatas.request;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;

import br.com.cincopatas.model.CuidadosVeterinarios;
import br.com.cincopatas.model.Endereco;
import br.com.cincopatas.model.Imagem;
import br.com.cincopatas.model.Personalidade;
import lombok.Data;

@Data
public class AnimalRequest {

	private Long id;
	private String nome;
	@NotBlank
	private String especie;
	@NotBlank
	private String sexo;
	@NotBlank
	private String porte;
	@NotBlank
	private String localizacao;
	private String infoExtras;
	private String nomeTitular;
	private LocalDate dataNasc;
	private Endereco endereco;
	@NotBlank
	private String status;
	private List<Personalidade> personalidades;
	private List<CuidadosVeterinarios> cuidadosVet;
	private String contato;
	private Imagem imagem;
}
