package br.com.cincopatas.dto;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

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
