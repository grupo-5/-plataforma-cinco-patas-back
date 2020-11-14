package br.com.cincopatas.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cincopatas.model.Pessoa;
import lombok.Data;

@Data
public class DepoimentoDTO {

	private Long id;
	private String texto;
	private LocalDate data;
	@JsonIgnore
	private Pessoa pessoa;
	private String tipo;

}
