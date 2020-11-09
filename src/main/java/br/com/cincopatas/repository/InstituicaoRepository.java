package br.com.cincopatas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cincopatas.model.Instituicao;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Long>{

	@Query(value= "select * from instituicao i where i.endereco_cidade_id = :id", nativeQuery = true)
	List<Instituicao> buscarInstituicoesCidade(Long id);
}