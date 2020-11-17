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

	@Query("from Pessoa p join p.instituicoes pi where pi.id= :codigo ORDER BY p.nome ASC")
	List<Pessoa> buscarPorInstituicao(Long codigo);
	
}
