package br.com.gerenciador.api.services;

import br.com.gerenciador.api.dtos.ClienteRequestDTO;
import br.com.gerenciador.api.dtos.ClienteResponseDTO;
import br.com.gerenciador.api.dtos.filters.ClienteFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClienteService {
    ClienteResponseDTO  criarCliente(ClienteRequestDTO dto);

    List<ClienteResponseDTO> listarTodosClientes();

    Page<ClienteResponseDTO> allPagedFiltred(int page, int size, ClienteFilter filter);

    ClienteResponseDTO buscarClientePeloId(Long id);

    ClienteResponseDTO atualizarClientePeloId(Long id, ClienteRequestDTO dto);

    void deletarClientePeloId(Long id);
}
