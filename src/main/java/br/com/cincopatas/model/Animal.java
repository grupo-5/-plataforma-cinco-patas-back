package br.com.cincopatas.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="animal")
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	private String nome;
	private String especie;
	private String sexo;
	private String porte;
	private String localizacao;
	
	@OneToMany(mappedBy="animal", cascade = CascadeType.ALL)
	private List<Personalidade> personalidades;// = new ArrayList<Personalidade>();

	@OneToMany(mappedBy="animal", cascade = CascadeType.ALL)
	private List<CuidadosVeterinarios> cuidadosVet;// = new ArrayList<CuidadosVeterinarios>();
	
	@Column(name= "info_extras")
	private String infoExtras;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "data_nasc")
	private LocalDate dataNasc;
	private String nomeTitular;
	private String contato;
	private String status;
	@Embedded
	private Endereco endereco;
	@OneToOne
	private Imagem imagem;
	
	@ManyToOne
	@JoinColumn(name = "instituicao_id", nullable=false) 
	private Instituicao instituicao;

}
