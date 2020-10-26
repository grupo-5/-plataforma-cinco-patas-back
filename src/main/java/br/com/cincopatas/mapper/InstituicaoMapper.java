package br.com.cincopatas.mapper;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cincopatas.dto.InstituicaoDTO;
import br.com.cincopatas.model.Instituicao;
import br.com.cincopatas.request.InstituicaoRequest;


@Component
public class InstituicaoMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public InstituicaoDTO modelToDTO(Instituicao instituicao) {
		return modelMapper.map(instituicao, InstituicaoDTO.class);
	}

	public Instituicao dtoRequestToModel(InstituicaoRequest instituicaoRequest) {
		return modelMapper.map(instituicaoRequest, Instituicao.class);
	}

	public InstituicaoDTO modelToDTO(Optional<Instituicao> instituicao) {
		return modelMapper.map(instituicao, InstituicaoDTO.class);
	}

}
