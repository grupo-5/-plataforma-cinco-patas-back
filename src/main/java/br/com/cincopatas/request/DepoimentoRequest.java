package br.com.cincopatas.request;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import br.com.cincopatas.model.Pessoa;
import lombok.Data;

@Data
public class DepoimentoRequest {
	private Long id;
	@NotBlank
	private String texto;
	@NotNull
	private Pessoa pessoa;
	private String tipo;

}
