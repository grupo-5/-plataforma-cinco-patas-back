package br.com.cincopatas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cincopatas.dto.InstituicaoDTO;
import br.com.cincopatas.email.EnvioEmailService;
import br.com.cincopatas.email.Mensagem;
import br.com.cincopatas.exception.OngNaoEncontradaException;
import br.com.cincopatas.mapper.InstituicaoMapper;
import br.com.cincopatas.model.Grupo;
import br.com.cincopatas.model.Instituicao;
import br.com.cincopatas.model.Usuario;
import br.com.cincopatas.repository.CidadeRepository;
import br.com.cincopatas.repository.EstadoRepository;
import br.com.cincopatas.repository.GrupoRepository;
import br.com.cincopatas.repository.InstituicaoRepository;
import br.com.cincopatas.repository.UsuarioRepository;
import br.com.cincopatas.request.InstituicaoRequest;

@Service
public class InstituicaoService {

	@Autowired
	private InstituicaoRepository instituicaoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private InstituicaoMapper instituicaoMapper;
	
	@Autowired
	private EnvioEmailService envioEmail;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private GrupoRepository grupoRepository;

	public List<InstituicaoDTO> listar() {
		List<Instituicao> instituicao = instituicaoRepository.findAll();
		return instituicao.stream().map(ani -> instituicaoMapper.modelToDTO(ani)).collect(Collectors.toList());
	}
	
	public InstituicaoDTO buscar(Long id) {
		Optional<Instituicao> instituicao = instituicaoRepository.findById(id);
	
		if(instituicao.isPresent()) {
			return instituicaoMapper.modelToDTO(instituicao.get());
		}
		return null;	
	}
	
	
	@Transactional
	public InstituicaoDTO salvar(InstituicaoRequest request) {

		Instituicao instituicao = instituicaoMapper.requestToModel(request);

		if (request.getEndereco().getCidade().getEstado().getId() == null) {
			estadoRepository.save(request.getEndereco().getCidade().getEstado());
			cidadeRepository.save(request.getEndereco().getCidade());
		}
		
		Mensagem mensagem = Mensagem.builder()
				.assunto(request.getNome() + " - Cadastro realizado")
				.corpo("cadastro-realizado.html")
				.variavel("pessoa", request)
				.destinatario(request.getEmail())
				.build();
		
		envioEmail.enviar(mensagem);
		
		Instituicao inst = instituicaoRepository.save(instituicao);
		
		Grupo grupo = grupoRepository.findById(2L).get();
		
		Usuario usuario = Usuario.builder()
				.nome(instituicao.getNome())
				.email(instituicao.getEmail())
				.senha(passwordEncoder.encode(instituicao.getSenha()))
				.grupo(grupo)
				.tipo(2L)
				.codigo(instituicao.getId())
		.build();
				
		usuarioRepository.save(usuario);

		return instituicaoMapper.modelToDTO(inst);
	}

	@Transactional
	public void remover(Long id) {
		try {
			instituicaoRepository.deleteById(id);
			instituicaoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new OngNaoEncontradaException(id);
		}
	}
	
	@Transactional
	public void atualizar(InstituicaoRequest instituicaoRequest) {
		Instituicao instituicao = instituicaoMapper.requestToModel(instituicaoRequest);
		
		if (instituicaoRequest.getEndereco().getCidade().getEstado().getId() == null) {
			estadoRepository.save(instituicaoRequest.getEndereco().getCidade().getEstado());
			cidadeRepository.save(instituicaoRequest.getEndereco().getCidade());
		}
	
		instituicaoMapper.modelToDTO(instituicaoRepository.save(instituicao));
	}

	public List<InstituicaoDTO> buscarInstituicoesCidade(Long id) {
		List<Instituicao> instituicao = instituicaoRepository.buscarInstituicoesCidade(id);
		return instituicao.stream()
				.map(inst -> instituicaoMapper
				.modelToDTO(inst))
				.collect(Collectors.toList());
	}

	public List<InstituicaoDTO> buscarPorEstado(Long id) {
		List<Instituicao> instituicao = instituicaoRepository.buscarPorEstado(id);
		
		return instituicao.stream()
				.map(inst -> instituicaoMapper.modelToDTO(inst))
				.collect(Collectors.toList());
	}
	
	public InstituicaoDTO buscarComCodigo(Long codigo) {
		Optional<Instituicao> instituicao = instituicaoRepository.findById(codigo);
	
		if(instituicao.isPresent()) {
			return instituicaoMapper.modelToDTO(instituicao.get());
		}
		return null;	
	}
	
}
