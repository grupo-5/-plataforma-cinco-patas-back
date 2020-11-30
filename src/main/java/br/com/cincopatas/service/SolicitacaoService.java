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
import br.com.cincopatas.email.EnvioEmailService;
import br.com.cincopatas.email.Mensagem;
import br.com.cincopatas.exception.SolicitacaoNaoEncontradaException;
import br.com.cincopatas.mapper.SolicitacaoMapper;
import br.com.cincopatas.model.Pessoa;
import br.com.cincopatas.model.Solicitacao;
import br.com.cincopatas.repository.InstituicaoRepository;
import br.com.cincopatas.repository.PessoaRepository;
import br.com.cincopatas.repository.SolicitacaoRepository;
import br.com.cincopatas.request.SolicitacaoRequest;
import br.com.cincopatas.security.permissoes.PatinhasSecurity;

@Service
public class SolicitacaoService {

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private EnvioEmailService envioEmail;
	@Autowired
	private PatinhasSecurity patinhasSecurity;
	@Autowired
	private SolicitacaoMapper solicitacaoMapper;
	
	public List<SolicitacaoDTO> listar() {
		List<Solicitacao> solicitacoes = solicitacaoRepository.findAll();
		return solicitacoes.stream()
					  .map(solicitacao -> solicitacaoMapper.modelToDTO(solicitacao))
					  .collect(Collectors.toList());
	}
	
	public List<SolicitacaoDTO> listarPorInstituicao(Long codigo) {
		List<Solicitacao> solicitacoes = solicitacaoRepository.buscarPorInstituicao(codigo);
		
		return solicitacoes.stream()
				.map(solicitacao -> solicitacaoMapper.modelToDTO(solicitacao))
				.collect(Collectors.toList());
	}
	
	public List<SolicitacaoDTO> listarPorPessoa(Long codigo) {
		List<Solicitacao> solicitacoes = solicitacaoRepository.buscarPorPessoa(codigo);
		
		return solicitacoes.stream()
				.map(solicitacao -> solicitacaoMapper.modelToDTO(solicitacao))
				.collect(Collectors.toList());
	}
	
	public SolicitacaoDTO buscar(Long id) {
		Optional<Solicitacao> solicitacao = solicitacaoRepository.findById(id);
	
		if(solicitacao.isPresent()) {
			return solicitacaoMapper.modelToDTO(solicitacao.get());
		}
		return null;	
	}
	
	@Transactional
	public SolicitacaoDTO salvar(SolicitacaoRequest solicitacaoRequest) {
		Solicitacao solicitacao = solicitacaoMapper.requestToModel(solicitacaoRequest);
		solicitacao.setData(OffsetDateTime.now());
	
//		for (SituacaoSolicitacao t : solicitacaoRequest.getSituacoes()) {
//			t.setSolicitacao(solicitacao);
//			t.setData(OffsetDateTime.now());
//		}
		
//		solicitacaoRequest.getSituacoes().stream().forEach(situacao -> 
//		situacao.setSolicitacao(solicitacao));
//		Pessoa pessoa = pessoaRepository.findById(solicitacaoRequest.getPessoa().getId()).get();
		
		Long codigo = patinhasSecurity.getCodigo();
		Pessoa pessoa = pessoaRepository.findById(codigo).get();

		solicitacao.setPessoa(pessoa);
		
		Mensagem mensagem = Mensagem.builder()
				.assunto("Solicitação - Enviada")
				.corpo("envio-solicitacao.html")
				.variavel("pessoa", pessoa)
				.destinatario(pessoa.getEmail())
				.build();
		envioEmail.enviar(mensagem);
		return solicitacaoMapper.modelToDTO(solicitacaoRepository.save(solicitacao));
	}
	
	@Transactional
	public void atualizar(SolicitacaoRequest solicitacaoRequest) {
		Solicitacao solicitacao = solicitacaoMapper.requestToModel(solicitacaoRequest);
		Optional<Solicitacao> soli = solicitacaoRepository.findById(solicitacao.getId());
		Pessoa pessoa = pessoaRepository.findById(solicitacaoRequest.getPessoa().getId()).get();
		solicitacao.setData(soli.get().getData());
		
		Mensagem mensagem = Mensagem.builder()
				.assunto("Solicitação " + " - Atualizada")
				.corpo("atualizacao-solicitacao.html")
				.variavel("pessoa", pessoa)
				.variavel("solicitacao", solicitacaoRequest)
				.destinatario(pessoa.getEmail())
				.build();
		
		envioEmail.enviar(mensagem);
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
