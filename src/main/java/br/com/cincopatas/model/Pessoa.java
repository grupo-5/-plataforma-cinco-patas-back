package br.com.cincopatas.model;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.cincopatas.enums.Sexo;
import br.com.cincopatas.enums.TipoPessoa;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private Integer tipo;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private Integer sexo;
	
	private String cpf;
	private String rg;
	private String email;
	private String contato;
	
	@Embedded
	private Endereco endereco;
	
	public Pessoa(Long id, TipoPessoa tipo, String nome, Sexo sexo, String cpf, String rg, String email, String contato) {
		
		this.id = id;
		this.tipo = tipo.getCod();
		this.nome = nome;
		this.sexo = sexo.getCod();
		this.cpf = cpf;
		this.rg = rg;
		this.email = email;
		this.contato = contato;
		
	}

	public TipoPessoa getTipo() {
		return TipoPessoa.toEnum(tipo);
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo.getCod();
	}

	public Sexo getSexo() {
		return Sexo.toEnum(sexo);
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo.getCod();
	}
	
}
