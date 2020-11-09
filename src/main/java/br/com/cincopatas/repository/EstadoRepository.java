package br.com.cincopatas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cincopatas.model.Cidade;
import br.com.cincopatas.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

	@Query("from Cidade where estado.id = :id ORDER BY nome ASC")
	List<Cidade> buscarCidades(Long id);

	@Query("from Estado ORDER BY nome ASC")
	List<Estado> findAllSorted();
}
