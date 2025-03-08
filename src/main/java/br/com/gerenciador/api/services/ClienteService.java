package br.com.gerenciador.api.services;

import br.com.gerenciador.api.dtos.ClienteRequestDTO;
import br.com.gerenciador.api.dtos.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {
    ClienteResponseDTO  criarCliente(ClienteRequestDTO dto);

    List<ClienteResponseDTO> listarTodosClientes();

    ClienteResponseDTO buscarClientePeloId(Long id);

    ClienteResponseDTO atualizarClientePeloId(Long id, ClienteRequestDTO dto);

    void deletarClientePeloId(Long id);
}
