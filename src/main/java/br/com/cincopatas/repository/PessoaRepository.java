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

//	@Query("from Pessoa p join p.instituicoes pi where p.id= :pi.pessoa_id and pi.instituicao_id= :codigo")
	@Query(value="SELECT * FROM pessoa p JOIN pessoa_instituicao pi WHERE p.id = pi.pessoa_id AND pi.instituicao_id = :codigo ORDER BY nome ASC", nativeQuery = true)
	List<Pessoa> buscarPorInstituicao(Long codigo);

	//SELECT * FROM pessoa p
	//JOIN pessoa_instituicao pi on p.id = pi.pessoa_id
		//	WHERE pi.instituicao_id = 1
}
