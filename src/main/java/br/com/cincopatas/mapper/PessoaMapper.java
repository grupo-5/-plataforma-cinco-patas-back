package br.com.cincopatas.mapper;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cincopatas.dto.PessoaDTO;
import br.com.cincopatas.model.Pessoa;
import br.com.cincopatas.request.PessoaRequest;

@Component
public class PessoaMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PessoaDTO modelToDTO(Pessoa Pessoa) {
		return modelMapper.map(Pessoa, PessoaDTO.class);
	}

	public Pessoa dtoRequestToModel(PessoaRequest PessoaRequest) {
		return modelMapper.map(PessoaRequest, Pessoa.class);
	}

}
