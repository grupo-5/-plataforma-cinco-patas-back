package br.com.cincopatas.model;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="solicitacao")
public class Solicitacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	private String situacao;
	@Column(name="tipo_solicitacao")
	private String tipoSolicitacao;
	private String justificativa;
	private OffsetDateTime data;
	
	@OneToOne
	@JoinColumn(name = "animal_id", nullable = false)
	private Animal animal;
	
	@OneToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Pessoa pessoa;
	
	@OneToMany(mappedBy = "solicitacao", cascade = CascadeType.ALL)
	private List<SituacaoSolicitacao> situacoes;

}
