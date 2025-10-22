package br.com.satc.demo.domain.repository;

import br.com.satc.demo.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
