package br.com.cincopatas.request;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import br.com.cincopatas.model.CuidadosVeterinarios;
import br.com.cincopatas.model.Endereco;
import br.com.cincopatas.model.Personalidade;
import lombok.Data;

@Data
public class AnimalRequest {

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
	private List<Personalidade> personalidades;
	private List<CuidadosVeterinarios> cuidadosVet ;
}
