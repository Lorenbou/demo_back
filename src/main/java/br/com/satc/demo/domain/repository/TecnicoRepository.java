package br.com.satc.demo.domain.repository;

import br.com.satc.demo.domain.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
}
