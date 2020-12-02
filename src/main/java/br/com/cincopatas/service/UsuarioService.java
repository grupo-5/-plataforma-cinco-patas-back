package br.com.cincopatas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cincopatas.dto.UsuarioDTO;
import br.com.cincopatas.mapper.UsuarioMapper;
import br.com.cincopatas.model.Usuario;
import br.com.cincopatas.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioMapper usuarioMapper;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioDTO buscarPorEmail(String email) {
		Optional <Usuario> usuario = usuarioRepository.findByEmail(email);
		return usuarioMapper.modelToDTO(usuario.get());
	}

}