package br.com.cincopatas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cincopatas.dto.PessoaDTO;
import br.com.cincopatas.email.EnvioEmailService;
import br.com.cincopatas.email.Mensagem;
import br.com.cincopatas.exception.PessoaNaoEncontradadException;
import br.com.cincopatas.mapper.PessoaMapper;
import br.com.cincopatas.model.Cidade;
import br.com.cincopatas.model.Grupo;
import br.com.cincopatas.model.Pessoa;
import br.com.cincopatas.model.Usuario;
import br.com.cincopatas.repository.CidadeRepository;
import br.com.cincopatas.repository.EstadoRepository;
import br.com.cincopatas.repository.GrupoRepository;
import br.com.cincopatas.repository.PessoaRepository;
import br.com.cincopatas.repository.UsuarioRepository;
import br.com.cincopatas.request.PessoaRequest;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private PessoaMapper pessoaMapper;
	
	@Autowired
	private EnvioEmailService envioEmail;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<PessoaDTO> listar() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return pessoas.stream()
					  .map(p -> pessoaMapper.modelToDTO(p))
					  .collect(Collectors.toList());
	}
	
	public PessoaDTO buscar(Long id ) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
				
		if(pessoa.isPresent()) {
			return pessoaMapper.modelToDTO(pessoa.get());
		}
		
		return null;	
	}
	

	@Transactional
	public PessoaDTO salvar(PessoaRequest request) {
		Pessoa p = pessoaMapper.dtoRequestToModel(request);
		
		if (request.getEndereco().getCidade().getEstado().getId() == null) {
			estadoRepository.save(request.getEndereco().getCidade().getEstado());
			cidadeRepository.save(request.getEndereco().getCidade());
		}		
		
		Cidade cidade = cidadeRepository.findById(request.getEndereco().getCidade().getId()).get();
		Mensagem mensagem = Mensagem.builder()
				.assunto(request.getNome() + " - Cadastro realizado")
				.corpo("cadastro-pessoa.html")
				.variavel("pessoa", request)
				.destinatario(request.getEmail())
				.build();
		
		envioEmail.enviar(mensagem);
		
		Pessoa pessoa = pessoaRepository.save(p);
		
		Grupo grupo = grupoRepository.findById(1L).get();
		
		Usuario usuario = Usuario.builder()
				.nome(pessoa.getNome())
				.email(pessoa.getEmail())
				.senha(passwordEncoder.encode("123"))
				.grupo(grupo)
				.tipo(1L)
				.codigo(pessoa.getId())
		.build();
				
		usuarioRepository.save(usuario);
		
		return pessoaMapper.modelToDTO(pessoa);
	}
	
	@Transactional
	public void atualizar(Pessoa obj) {
		pessoaRepository.save(obj);
	}
	
	@Transactional
	public void excluir(Long id) {
		
		try {
			pessoaRepository.deleteById(id);
			pessoaRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new PessoaNaoEncontradadException(id);
		};			
	}

}
