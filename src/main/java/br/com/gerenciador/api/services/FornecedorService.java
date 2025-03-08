package br.com.gerenciador.api.services;

import br.com.gerenciador.api.dtos.FornecedorRequestDTO;
import br.com.gerenciador.api.dtos.FornecedorResponseDTO;

import java.util.List;

public interface FornecedorService {

    FornecedorResponseDTO criarFornecedor(FornecedorRequestDTO dto);

    List<FornecedorResponseDTO> listarTodosFornecedores();

    FornecedorResponseDTO buscarFornecedorPorId(Long id);

    FornecedorResponseDTO atualizarFornecedorPeloId(Long id, FornecedorRequestDTO dto);
}
