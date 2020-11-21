package br.com.cincopatas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cincopatas.model.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{


	
}
