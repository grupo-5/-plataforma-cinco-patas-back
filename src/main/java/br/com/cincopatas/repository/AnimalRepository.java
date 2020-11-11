package br.com.cincopatas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cincopatas.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>{

	@Query("from Animal ORDER BY nome ASC")
	List<Animal> findAllSorted();
}
