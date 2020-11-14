package br.com.cincopatas.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cincopatas.dto.SituacaoSolicitacaoDTO;
import br.com.cincopatas.model.SituacaoSolicitacao;
import br.com.cincopatas.request.SituacaoSolicitacaoRequest;

@Component
public class SituacaoSolicitacaoMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public SituacaoSolicitacaoDTO modelToDTO(SituacaoSolicitacao situacaoSolicitacao) {
		return modelMapper.map(situacaoSolicitacao, SituacaoSolicitacaoDTO.class);
	}

	public SituacaoSolicitacao requestToModel(SituacaoSolicitacaoRequest situacaoSolicitacaoRequest) {
		return modelMapper.map(situacaoSolicitacaoRequest, SituacaoSolicitacao.class);
	}
}
