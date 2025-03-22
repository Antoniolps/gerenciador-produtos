package br.com.gerenciador.api.services;

import br.com.gerenciador.api.dtos.ProdutoRequestDTO;
import br.com.gerenciador.api.dtos.ProdutoResponseDTO;

import java.util.List;

public interface ProdutoService {
    ProdutoResponseDTO criarProduto(ProdutoRequestDTO produtoRequestDto);

    ProdutoResponseDTO buscarProdutoPorId(Long id);

    List<ProdutoResponseDTO> buscarProdutos();

    ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO produtoRequestDto);

    void deletarProduto(Long id);

}
