package br.com.gerenciador.api.repositories;

import br.com.gerenciador.api.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
