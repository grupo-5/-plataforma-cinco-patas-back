package br.com.cincopatas.service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cincopatas.dto.DepoimentoDTO;
import br.com.cincopatas.mapper.DepoimentoMapper;
import br.com.cincopatas.model.Depoimento;
import br.com.cincopatas.repository.DepoimentoRepository;
import br.com.cincopatas.request.DepoimentoRequest;

@Service
public class DepoimentoService {

	@Autowired
	private DepoimentoRepository depoimentoRepository;
	@Autowired
	private DepoimentoMapper depoimentoMapper;

	@Transactional
	public DepoimentoDTO salvar(DepoimentoRequest depoimentoRequest) {

		Depoimento depoimento = depoimentoMapper.requestToModel(depoimentoRequest);
		depoimento.setData(LocalDate.now());

		return depoimentoMapper.modelToDTO(depoimentoRepository.save(depoimento));

	}

	public List<DepoimentoDTO> listar() {
		List<Depoimento> depoimentos = depoimentoRepository.findAll();
		return depoimentos.stream().map(depo -> depoimentoMapper.modelToDTO(depo)).collect(Collectors.toList());
	}

	public Optional<Depoimento> buscar(Long id) {
		return this.depoimentoRepository.findById(id);
	}

}
