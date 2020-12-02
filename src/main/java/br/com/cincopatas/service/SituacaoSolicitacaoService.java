package br.com.cincopatas.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cincopatas.dto.SituacaoSolicitacaoDTO;
import br.com.cincopatas.email.EnvioEmailService;
import br.com.cincopatas.email.Mensagem;
import br.com.cincopatas.mapper.SituacaoSolicitacaoMapper;
import br.com.cincopatas.model.Animal;
import br.com.cincopatas.model.Pessoa;
import br.com.cincopatas.model.SituacaoSolicitacao;
import br.com.cincopatas.model.Solicitacao;
import br.com.cincopatas.repository.AnimalRepository;
import br.com.cincopatas.repository.InstituicaoRepository;
import br.com.cincopatas.repository.PessoaRepository;
import br.com.cincopatas.repository.SituacaoSolicitacaoRepository;
import br.com.cincopatas.repository.SolicitacaoRepository;
import br.com.cincopatas.request.AnimalRequest;
import br.com.cincopatas.request.SituacaoSolicitacaoRequest;
import br.com.cincopatas.security.permissoes.PatinhasSecurity;

@Service
public class SituacaoSolicitacaoService {

	@Autowired
	private SituacaoSolicitacaoRepository situacaoSolicitacaoRepository;
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private EnvioEmailService envioEmail;
	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private AnimalService animalService;
	@Autowired
	private PatinhasSecurity patinhasSecurity;
	@Autowired
	private SituacaoSolicitacaoMapper situacaoSolicitacaoMapper;
	
	public List<SituacaoSolicitacaoDTO> listar() {
		List<SituacaoSolicitacao> situacaoSolicitacoes = situacaoSolicitacaoRepository.findAll();
		return situacaoSolicitacoes.stream()
					  .map(situacaoSolicitacao -> situacaoSolicitacaoMapper.modelToDTO(situacaoSolicitacao))
					  .collect(Collectors.toList());
	}
	
	public SituacaoSolicitacaoDTO buscar(Long id) {
		Optional<SituacaoSolicitacao> situacaoSolicitacao = situacaoSolicitacaoRepository.findById(id);
	
		if(situacaoSolicitacao.isPresent()) {
			return situacaoSolicitacaoMapper.modelToDTO(situacaoSolicitacao.get());
		}
		return null;	
	}
	
	public boolean solicitacoesRecebidas(Long id) {
		int recebidas = situacaoSolicitacaoRepository.solicitacoesRecebidas(id);
		
		if(recebidas>0) {
			return true;
		}
		return false;	
	}
	
	@Transactional
	public SituacaoSolicitacaoDTO salvar(SituacaoSolicitacaoRequest situacaoSolicitacaoRequest) {
		SituacaoSolicitacao situacaoSolicitacao = situacaoSolicitacaoMapper.requestToModel(situacaoSolicitacaoRequest);
		situacaoSolicitacao.setData(OffsetDateTime.now());

		Optional <Solicitacao> solici = solicitacaoRepository.findById(situacaoSolicitacao.getSolicitacao().getId());
		Optional <Pessoa> pessoa = pessoaRepository.findById(solici.get().getPessoa().getId());
		Animal animal =  animalService.buscarNormal(solici.get().getAnimal().getId());
		
//		Long codigo = patinhasSecurity.getCodigo();
//		Instituicao instt = instituicaoRepository.findById(codigo).get();
		
		if(situacaoSolicitacaoRequest.getSituacao().equals("Recebida")) {
			animal.setStatus("Em Adoção");
			animalService.atualizarA(animal);
		}else if(situacaoSolicitacaoRequest.getSituacao().equals("Aceita")) {
			if(solici.get().getTipoSolicitacao().equals("Adoção")) {
				animal.setStatus("Adotado");
				animalService.atualizarA(animal);
			}else if(solici.get().getTipoSolicitacao().equals("Lar Temporário")) {
				animal.setStatus("Tutelado");
				animalService.atualizarA(animal);
			}
		}else if(situacaoSolicitacaoRequest.getSituacao().equals("Recusada")) {
			animal.setStatus("Disponível");
			animalService.atualizarA(animal);
		}
		
		Mensagem mensagem = Mensagem.builder()
				.assunto("Solicitação " + " - Atualizada")
				.corpo("atualizacao-solicitacao.html")
				.variavel("pessoa", pessoa.get())
//				.variavel("solicitacao", situacaoSolicitacaoRequest.getSituacao())
				.destinatario(pessoa.get().getEmail())
				.build();
		
		envioEmail.enviar(mensagem);
		return situacaoSolicitacaoMapper.modelToDTO(situacaoSolicitacaoRepository.save(situacaoSolicitacao));
	}
	
	
}
