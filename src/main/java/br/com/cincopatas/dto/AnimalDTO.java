package br.com.cincopatas.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.cincopatas.model.CuidadosVeterinarios;
import br.com.cincopatas.model.Endereco;
import br.com.cincopatas.model.Personalidade;
import lombok.Data;

@Data
public class AnimalDTO {

	private Long id;
	private String nome;
	private String especie;
	private String sexo;
	private String porte;
	private String localizacao;
	private String infoExtras;
	private String nomeTitular;
	private LocalDate dataNasc;
	private Endereco endereco;
	private String status;
	private List<Personalidade> personalidades;
	private List<CuidadosVeterinarios> cuidadosVet ;
	private String contato;
	private ImagemDTO imagem;
}
