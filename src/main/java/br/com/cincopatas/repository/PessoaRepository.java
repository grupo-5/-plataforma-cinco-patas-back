package br.com.cincopatas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cincopatas.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	@Query("from Pessoa ORDER BY nome ASC")
	List<Pessoa> findAllSorted();

}
