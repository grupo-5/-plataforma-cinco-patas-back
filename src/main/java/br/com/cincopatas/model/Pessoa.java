package br.com.cincopatas.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String dataNasc;
	private String sexo;
	private String tipo;
	private String cpf;
	private String rg;	
	private String email;
	
	@Transient
	private String senha;
	
	private String contato;
	
	@Embedded
	private Endereco endereco;
	
	@ManyToMany
	@JoinTable(name = "pessoa_instituicao", joinColumns = @JoinColumn(name = "pessoa_id"),
			inverseJoinColumns = @JoinColumn(name = "instituicao_id"))
	private Set<Instituicao> instituicoes = new HashSet<>();
	
}
