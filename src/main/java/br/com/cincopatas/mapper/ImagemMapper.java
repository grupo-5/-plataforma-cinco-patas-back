package br.com.cincopatas.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cincopatas.dto.ImagemDTO;
import br.com.cincopatas.model.Imagem;
import br.com.cincopatas.request.ImagemRequest;

@Component
public class ImagemMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Imagem requestToModel(ImagemRequest imagemRequest) {
		return modelMapper.map(imagemRequest, Imagem.class);
	}
	
	public ImagemDTO modelToDTO(Imagem imagem) {
		return modelMapper.map(imagem, ImagemDTO.class);
	}

}
