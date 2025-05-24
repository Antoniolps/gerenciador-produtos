package br.com.gerenciador.api.services;

import br.com.gerenciador.api.dtos.ProdutoRequestDTO;
import br.com.gerenciador.api.dtos.ProdutoResponseDTO;
import br.com.gerenciador.api.dtos.filters.ProdutoFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProdutoService {
    ProdutoResponseDTO criarProduto(ProdutoRequestDTO produtoRequestDto);

    ProdutoResponseDTO buscarProdutoPorId(Long id);

    List<ProdutoResponseDTO> buscarProdutos();

    Page<ProdutoResponseDTO> allPagedFiltered(int page, int size, ProdutoFilter filter);

    ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO produtoRequestDto);

    void deletarProduto(Long id);

}
