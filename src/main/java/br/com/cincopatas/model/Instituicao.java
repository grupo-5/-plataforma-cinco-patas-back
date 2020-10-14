package br.com.cincopatas.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Instituicao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	@Column
	private String nome;
	@Column
	private String documento;
	@Column
	private String tipoDeDocumento;
	@Column
	private String email;
	@Column
	private String contato;
	@Column
	private String razaoSocial;
	@Column
	private String inscricaoEstadual;
	@Column
	private Long capacidade;
	@Column
	private String banco;
	@Column
	private String agencia;
	@Column
	private String conta;
	@Embedded
	private Endereco endereco;

}
