package br.com.cincopatas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cincopatas.model.Cidade;
import br.com.cincopatas.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	public List<Cidade> listar() {
		return cidadeRepository.findAllSorted();
	}

	@Transactional
	public void salvar(Cidade cidade) {
		cidadeRepository.save(cidade);
	}
}
