package br.com.cincopatas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cincopatas.model.Solicitacao;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>{

}
