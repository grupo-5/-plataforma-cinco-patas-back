package br.com.cincopatas.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cincopatas.dto.SolicitacaoDTO;
import br.com.cincopatas.model.Solicitacao;
import br.com.cincopatas.request.SolicitacaoRequest;

@Component
public class SolicitacaoMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public SolicitacaoDTO modelToDTO(Solicitacao solicitacao) {
		return modelMapper.map(solicitacao, SolicitacaoDTO.class);
	}

	public Solicitacao dtoRequestToModel(SolicitacaoRequest solicitacaoRequest) {
		return modelMapper.map(solicitacaoRequest, Solicitacao.class);
	}
}
