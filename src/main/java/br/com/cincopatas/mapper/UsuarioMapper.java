package br.com.cincopatas.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cincopatas.dto.UsuarioDTO;
import br.com.cincopatas.model.Usuario;
import br.com.cincopatas.request.UsuarioRequest;

@Component
public class UsuarioMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UsuarioDTO modelToDTO(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioDTO.class);
	}

	public Usuario requestToModel(UsuarioRequest usuarioRequest) {
		return modelMapper.map(usuarioRequest, Usuario.class);
	}

}
