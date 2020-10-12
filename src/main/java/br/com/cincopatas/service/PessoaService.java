package br.com.cincopatas.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cincopatas.dto.PessoaDTO;
import br.com.cincopatas.mapper.PessoaMapper;
import br.com.cincopatas.model.Pessoa;
import br.com.cincopatas.repository.CidadeRepository;
import br.com.cincopatas.repository.EstadoRepository;
import br.com.cincopatas.repository.PessoaRepository;
import br.com.cincopatas.request.PessoaRequest;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private PessoaMapper pessoaMapper;

	public List<PessoaDTO> listar() {
		List<Pessoa> animais = pessoaRepository.findAll();
		return animais.stream()
					  .map(ani -> pessoaMapper.modelToDTO(ani))
					  .collect(Collectors.toList());
	}
	
	@Transactional
	public PessoaDTO salvar(PessoaRequest request) {
		Pessoa p = pessoaMapper.dtoRequestToModel(request);
		
		if (request.getEndereco().getCidade().getEstado().getId() == null) {
			estadoRepository.save(request.getEndereco().getCidade().getEstado());
			cidadeRepository.save(request.getEndereco().getCidade());
		}
		
		return pessoaMapper.modelToDTO(pessoaRepository.save(p));
	}

}
