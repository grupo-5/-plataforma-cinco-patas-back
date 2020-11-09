package br.com.cincopatas.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.cincopatas.dto.SolicitacaoDTO;
import br.com.cincopatas.exception.SolicitacaoNaoEncontradaException;
import br.com.cincopatas.mapper.SolicitacaoMapper;
import br.com.cincopatas.model.SituacaoSolicitacao;
import br.com.cincopatas.model.Solicitacao;
import br.com.cincopatas.repository.SolicitacaoRepository;
import br.com.cincopatas.request.SolicitacaoRequest;

@Service
public class SolicitacaoService {

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	@Autowired
	private SolicitacaoMapper solicitacaoMapper;
	
	public List<SolicitacaoDTO> listar() {
		List<Solicitacao> solicitacoes = solicitacaoRepository.findAll();
		return solicitacoes.stream()
					  .map(solicitacao -> solicitacaoMapper.modelToDTO(solicitacao))
					  .collect(Collectors.toList());
	}
	
	public Optional<Solicitacao> buscar(Long id) {
		return this.solicitacaoRepository.findById(id);
	}
	
	@Transactional
	public SolicitacaoDTO salvar(SolicitacaoRequest solicitacaoRequest) {
		Solicitacao solicitacao = solicitacaoMapper.dtoRequestToModel(solicitacaoRequest);
		solicitacao.setData(OffsetDateTime.now());
	
		for (SituacaoSolicitacao t : solicitacaoRequest.getSituacoes()) {
			t.setSolicitacao(solicitacao);
			t.setData(OffsetDateTime.now());
		}
		
//		solicitacaoRequest.getSituacoes().stream().forEach(situacao -> 
//		situacao.setSolicitacao(solicitacao));
		return solicitacaoMapper.modelToDTO(solicitacaoRepository.save(solicitacao));
	}
	
	@Transactional
	public void atualizar(Solicitacao solicitacao) {

		solicitacaoMapper.modelToDTO(solicitacaoRepository.save(solicitacao));
	}
	
	@Transactional
	public void remover(Long id) {
		try {
			solicitacaoRepository.deleteById(id);
			solicitacaoRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new SolicitacaoNaoEncontradaException(id);
		};			
	}
	
}
