package br.com.cincopatas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cincopatas.model.Cidade;
import br.com.cincopatas.model.Estado;
import br.com.cincopatas.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Estado> listar() {
		return estadoRepository.findAll();
	}

	@Transactional
	public void salvar(Estado estado) {
		estadoRepository.save(estado);
	}

	public List<Cidade> buscarCidades(Long id) {
		return estadoRepository.buscarCidades(id);
	}

}
