package br.com.cincopatas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.cincopatas.dto.AnimalDTO;
import br.com.cincopatas.exception.AnimalNaoEncontradodException;
import br.com.cincopatas.filtro.AnimalFiltro;
import br.com.cincopatas.mapper.AnimalMapper;
import br.com.cincopatas.model.Animal;
import br.com.cincopatas.model.Instituicao;
import br.com.cincopatas.repository.AnimalRepository;
import br.com.cincopatas.repository.CidadeRepository;
import br.com.cincopatas.repository.EstadoRepository;
import br.com.cincopatas.repository.InstituicaoRepository;
import br.com.cincopatas.request.AnimalRequest;
import br.com.cincopatas.security.permissoes.PatinhasSecurity;

@Service
public class AnimalService {

	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired 
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private PatinhasSecurity patinhasSecurity;
	@Autowired
	private AnimalMapper animalMapper;

	public List<AnimalDTO> listar() {
		List<Animal> animais = animalRepository.findAll();
		return animais.stream().map(ani -> animalMapper.modelToDTO(ani)).collect(Collectors.toList());
	}
	
	public List<AnimalDTO> listarPorInstituicao(Long codigo) {
		List<Animal> animais = animalRepository.buscarPorInstituicao(codigo);
		return animais.stream().map(ani -> animalMapper.modelToDTO(ani)).collect(Collectors.toList());
	}

	// Lista animais por filtro e que estejam com status disponível
	public List<AnimalDTO> listarPorFiltro(AnimalFiltro filtro) {
		List<Animal> animais = animalRepository.findAll(
				filtro.getCidade(), 
				filtro.getEstado(), 
				filtro.getPorte(),
				filtro.getSexo(), 
				filtro.getEspecie());
		return animais.stream().map(ani -> animalMapper.modelToDTO(ani)).collect(Collectors.toList());
	}


	public AnimalDTO buscar(Long id) {
		Optional<Animal> animal = animalRepository.findById(id);

		if (animal.isPresent()) {
			return animalMapper.modelToDTO(animal.get());
		}
		return null;
	}
	
	public Animal buscarNormal(Long id) {
		Optional<Animal> animal = animalRepository.findById(id);
		
		if (animal.isPresent()) {
			return animal.get();
		}
		return null;
	}

	@Transactional
	public AnimalDTO salvar(AnimalRequest animalRequest) {

		Animal animal = animalMapper.requestToModel(animalRequest);
		Long codigo = patinhasSecurity.getCodigo();
		Instituicao inst = instituicaoRepository.findById(codigo).get();

		if (animalRequest.getEndereco().getCidade().getEstado().getId() == null) {
			estadoRepository.save(animalRequest.getEndereco().getCidade().getEstado());
			cidadeRepository.save(animalRequest.getEndereco().getCidade());
		}
		animal.setInstituicao(inst);
		animalRequest.getPersonalidades().stream().forEach(personalidade -> personalidade.setAnimal(animal));
		animalRequest.getCuidadosVet().stream().forEach(cuidados -> cuidados.setAnimal(animal));
		return animalMapper.modelToDTO(animalRepository.save(animal));
	}

	@Transactional
	public void remover(Long id) {
		try {
			animalRepository.deleteById(id);
			animalRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new AnimalNaoEncontradodException(id);
		}
		;
	}

	@Transactional
	public void atualizar(AnimalRequest animalRequest) {
		Animal animal = animalMapper.requestToModel(animalRequest);

		if (animalRequest.getEndereco().getCidade().getEstado().getId() == null) {
			estadoRepository.save(animalRequest.getEndereco().getCidade().getEstado());
			cidadeRepository.save(animalRequest.getEndereco().getCidade());
		}

		animalRequest.getPersonalidades().stream().forEach(personalidade -> personalidade.setAnimal(animal));

		animalRequest.getCuidadosVet().stream().forEach(cuidados -> cuidados.setAnimal(animal));
		animalMapper.modelToDTO(animalRepository.save(animal));
	}
	
	@Transactional
	public void atualizarA(Animal animal) {
		
		
		if (animal.getEndereco().getCidade().getEstado().getId() == null) {
			estadoRepository.save(animal.getEndereco().getCidade().getEstado());
			cidadeRepository.save(animal.getEndereco().getCidade());
		}
		
		animal.getPersonalidades().stream().forEach(personalidade -> personalidade.setAnimal(animal));
		
		animal.getCuidadosVet().stream().forEach(cuidados -> cuidados.setAnimal(animal));
		animalMapper.modelToDTO(animalRepository.save(animal));
	}

}