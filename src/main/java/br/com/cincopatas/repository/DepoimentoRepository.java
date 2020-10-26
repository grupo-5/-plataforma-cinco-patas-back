package br.com.cincopatas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.cincopatas.model.Depoimento;


@Repository
public interface DepoimentoRepository extends JpaRepository<Depoimento, Long> {


}
