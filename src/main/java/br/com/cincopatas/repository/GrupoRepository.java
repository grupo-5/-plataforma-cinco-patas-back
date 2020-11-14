package br.com.cincopatas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cincopatas.model.Grupo;

@Repository
public interface GrupoRepository  extends JpaRepository<Grupo, Long>{

}
