package br.com.gerenciador.api.repositories;

import br.com.gerenciador.api.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
