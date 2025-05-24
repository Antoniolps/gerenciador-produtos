package br.com.gerenciador.api.services;

import br.com.gerenciador.api.dtos.FornecedorRequestDTO;
import br.com.gerenciador.api.dtos.FornecedorResponseDTO;
import br.com.gerenciador.api.dtos.filters.FornecedorFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FornecedorService {

    FornecedorResponseDTO criarFornecedor(FornecedorRequestDTO dto);

    List<FornecedorResponseDTO> listarTodosFornecedores();

    Page<FornecedorResponseDTO> allPagedFiltered(int page, int size, FornecedorFilter filter);

    FornecedorResponseDTO buscarFornecedorPeloId(Long id);

    FornecedorResponseDTO atualizarFornecedorPeloId(Long id, FornecedorRequestDTO dto);

    void deletarFornecedorPeloId(Long id);
}
