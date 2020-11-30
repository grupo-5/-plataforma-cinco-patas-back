package br.com.cincopatas.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

	private Long id;
	private Long tipo;
	private Long codigo;
	private String email;
}
