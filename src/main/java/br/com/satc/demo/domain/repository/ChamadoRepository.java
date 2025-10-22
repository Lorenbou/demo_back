package br.com.satc.demo.domain.repository;

import br.com.satc.demo.domain.model.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
}
