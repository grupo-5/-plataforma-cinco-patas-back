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
	

	@Query("from Animal a where (:cidade is null or a.endereco.cidade.id = :cidade)"
			+ "and a.status LIKE 'Disponível'"
			+ " and "
			+ "(:estado is null or a.endereco.cidade.estado.id = :estado) and "
			+ "(:porte is null or a.porte like :porte) and "
			+ "(:sexo is null or a.sexo like :sexo) and "
			+ "(:especie is null or a.especie like :especie) ORDER BY a.id DESC")
	List<Animal> findAll(Long cidade, Long estado, String porte, String sexo, String especie);

	@Query("from Animal a where a.instituicao.id = :codigo ORDER BY nome ASC")
	List<Animal> buscarPorInstituicao(Long codigo);
	
//	@Query("select count(nome) from Animal a where a.instituicao.id = :codigo")
//	List<Animal> contarAnimalInstituicao(Long codigo);
	
	
}
