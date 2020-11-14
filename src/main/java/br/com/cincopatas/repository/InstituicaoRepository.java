package br.com.cincopatas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cincopatas.model.Instituicao;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Long>{

	@Query("from Instituicao i where i.endereco.cidade.id = :id")
	List<Instituicao> buscarInstituicoesCidade(Long id);

	@Query("from Instituicao i where i.endereco.cidade.estado.id = :id")
	List<Instituicao> buscarPorEstado(Long id);
}