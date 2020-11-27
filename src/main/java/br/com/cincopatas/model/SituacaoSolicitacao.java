package br.com.cincopatas.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="situacao_solicitacao")
public class SituacaoSolicitacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String situacao;
	private OffsetDateTime data;
	@JsonIgnore	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="solicitacao_id", nullable = false) 
	private Solicitacao solicitacao;
}
