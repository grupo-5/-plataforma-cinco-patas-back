package br.com.cincopatas.security.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.cincopatas.model.Usuario;
import lombok.Getter;

@Getter
public class AuthUser extends User {

private static final long serialVersionUID = 1L;
	
	private Long tipo;
	private Long codigo;
	private String nomeCompleto;

	public AuthUser(Usuario usuario, Collection<? extends GrantedAuthority> permissoes) {
		super(usuario.getEmail(), usuario.getSenha(), permissoes);
		
		this.tipo = usuario.getTipo();
		this.codigo = usuario.getCodigo();
		this.nomeCompleto = usuario.getNome();
	}

}
