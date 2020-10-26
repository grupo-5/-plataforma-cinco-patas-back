package br.com.cincopatas.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cincopatas.dto.DepoimentoDTO;
import br.com.cincopatas.model.Depoimento;
import br.com.cincopatas.request.DepoimentoRequest;

@Component
public class DepoimentoMapper {
	

	@Autowired
	private ModelMapper modelMapper;
	
	public Depoimento requestToModel(DepoimentoRequest  depoimentoRequest) {
		return modelMapper.map(depoimentoRequest, Depoimento.class);
	}
	
	public DepoimentoDTO modelToDTO(Depoimento depoimento) {
		return modelMapper.map(depoimento, DepoimentoDTO.class);
	}


}
