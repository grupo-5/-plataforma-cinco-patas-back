package br.com.cincopatas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cincopatas.model.Imagem;

@Repository
public interface ImagemRepository  extends JpaRepository<Imagem, Long>{

}
