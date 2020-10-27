package br.com.cincopatas.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
	private String nome;
	@Column(name="numero_documento")
	private String numeroDocumento;
	@Column(name="tipo_documento")
	private String tipoDocumento;
	private String email;
	private String contato;
	private String razaoSocial;
	private String inscricaoEstadual;
	private Long capacidade;
	private String banco;
	private String agencia;
	private String conta;
	@Embedded
	private Endereco endereco;
	@OneToOne
	private Imagem imagem;
}
