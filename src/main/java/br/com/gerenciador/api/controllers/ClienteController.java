package br.com.gerenciador.api.controllers;

import br.com.gerenciador.api.dtos.ClienteRequestDTO;
import br.com.gerenciador.api.dtos.ClienteResponseDTO;
import br.com.gerenciador.api.dtos.filters.ClienteFilter;
import br.com.gerenciador.api.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    ResponseEntity<ClienteResponseDTO> criarCliente(@Valid @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.criarCliente(dto));
    }

    @GetMapping
    ResponseEntity<List<ClienteResponseDTO>> listarTodosClientes() {
        return ResponseEntity.ok(clienteService.listarTodosClientes());
    }

    @GetMapping("/all-filtered/{page}/{size}")
    public  ResponseEntity<Page<ClienteResponseDTO>> getAllFiltered(@PathVariable int page,
                                                   @PathVariable int size,
                                                   ClienteFilter filter){
        return ResponseEntity.ok(clienteService.allPagedFiltred(page, size, filter));
    }

    @GetMapping("/{id}")
    ResponseEntity<ClienteResponseDTO> buscarClientePeloId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarClientePeloId(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<ClienteResponseDTO> atualizarClientePeloId(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.ok(clienteService.atualizarClientePeloId(id, dto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletarClientePeloId(@PathVariable Long id) {
        clienteService.deletarClientePeloId(id);
        return ResponseEntity.noContent().build();
    }
}
