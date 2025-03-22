package br.com.gerenciador.api.services;

import br.com.gerenciador.api.dtos.ClienteRequestDTO;
import br.com.gerenciador.api.dtos.ClienteResponseDTO;
import br.com.gerenciador.api.mappers.ClienteMapper;
import br.com.gerenciador.api.mappers.EnderecoMapper;
import br.com.gerenciador.api.models.Cliente;
import br.com.gerenciador.api.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final EnderecoMapper enderecoMapper;

    @Override
    public ClienteResponseDTO criarCliente(ClienteRequestDTO dto) {
        Cliente cliente = clienteMapper.toEntity(dto);

        return clienteMapper.toDTO(clienteRepository.save(cliente));
    }

    @Override
    public List<ClienteResponseDTO> listarTodosClientes() {
        return clienteRepository.findAll().stream().map(clienteMapper::toDTO).toList();
    }

    @Override
    public ClienteResponseDTO buscarClientePeloId(Long id) {
        return clienteRepository.findById(id).map(clienteMapper::toDTO).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado")
        );
    }

    @Override
    @Transactional
    public ClienteResponseDTO atualizarClientePeloId(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado")
        );

        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setEndereco(enderecoMapper.toEntity(dto.endereco()));

        return clienteMapper.toDTO(clienteRepository.save(cliente));
    }

    @Override
    @Transactional
    public void deletarClientePeloId(Long id) {
        if(!clienteRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }

        clienteRepository.deleteById(id);
    }
}
