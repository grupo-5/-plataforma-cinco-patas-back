package br.com.cincopatas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cincopatas.model.SituacaoSolicitacao;

public interface SituacaoSolicitacaoRepository extends JpaRepository<SituacaoSolicitacao, Long>{

	@Query(value="SELECT COUNT(*) FROM situacao_solicitacao WHERE situacao LIKE 'Recebida' AND solicitacao_id = :id", nativeQuery = true)
	int solicitacoesRecebidas(Long id);
}
