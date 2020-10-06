package br.com.cincopatas.mapper;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cincopatas.dto.AnimalDTO;
import br.com.cincopatas.model.Animal;
import br.com.cincopatas.request.AnimalRequest;

@Component
public class AnimalMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AnimalDTO modelToDTO(Animal animal) {
		return modelMapper.map(animal, AnimalDTO.class);
	}

	public Animal dtoRequestToModel(AnimalRequest animalRequest) {
		return modelMapper.map(animalRequest, Animal.class);
	}

	public AnimalDTO modelToDTO(Optional<Animal> animal) {
		return modelMapper.map(animal, AnimalDTO.class);
	}

}
