package br.com.gerenciador.api.services;

import br.com.gerenciador.api.dtos.ProdutoRequestDTO;
import br.com.gerenciador.api.dtos.ProdutoResponseDTO;
import br.com.gerenciador.api.mappers.ProdutoMapper;
import br.com.gerenciador.api.models.Fornecedor;
import br.com.gerenciador.api.models.Produto;
import br.com.gerenciador.api.repositories.FornecedorRepository;
import br.com.gerenciador.api.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final FornecedorRepository fornecedorRepository;
    private final ProdutoMapper produtoMapper;

    @Override
    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO dto) {
        Produto produto = produtoMapper.toEntity(dto);
        produto.setFornecedor(buscarFornecedor(dto.fornecedorId()));
        return produtoMapper.toDto(produtoRepository.save(produto));
    }

    @Override
    public ProdutoResponseDTO buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .map(produtoMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @Override
    public List<ProdutoResponseDTO> buscarProdutos() {
        return produtoRepository.findAll().stream()
                .map(produtoMapper::toDto)
                .toList();
    }

    @Override
    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO produtoRequestDto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        produto.setNome(produtoRequestDto.nome());
        produto.setPreco(produtoRequestDto.preco());
        produto.setDescricao(produtoRequestDto.descricao());
        produto.setQuantidadeEstoque(produtoRequestDto.quantidadeEstoque());
        produto.setFornecedor(buscarFornecedor(produtoRequestDto.fornecedorId()));

        return produtoMapper.toDto(produtoRepository.save(produto));
    }

    @Override
    public void deletarProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        produtoRepository.delete(produto);
    }

    private Fornecedor buscarFornecedor(Long id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado"));
    }
}
