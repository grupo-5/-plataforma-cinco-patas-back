package br.com.cincopatas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "estado")
public class Estado {

	@Id 
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	private String sigla;

}
