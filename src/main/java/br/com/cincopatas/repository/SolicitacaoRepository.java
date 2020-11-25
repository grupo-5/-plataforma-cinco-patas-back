package br.com.cincopatas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cincopatas.model.Solicitacao;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>{

	@Query("from Solicitacao s where s.animal.instituicao.id = :codigo ORDER BY s.id DESC")
	List<Solicitacao> buscarPorInstituicao(Long codigo);
	
	@Query("from Solicitacao s where s.pessoa.id = :codigo ORDER BY s.id DESC")
	List<Solicitacao> buscarPorPessoa(Long codigo);

}
