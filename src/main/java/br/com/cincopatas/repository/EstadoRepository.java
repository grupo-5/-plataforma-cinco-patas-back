package br.com.cincopatas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cincopatas.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {


}
