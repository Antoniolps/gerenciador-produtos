package br.com.gerenciador.api.controllers;

import br.com.gerenciador.api.dtos.ClienteRequestDTO;
import br.com.gerenciador.api.dtos.ClienteResponseDTO;
import br.com.gerenciador.api.dtos.filters.ClienteFilter;
import br.com.gerenciador.api.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Operation(summary = "Cria um novo cliente")
    @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso")
    @PostMapping
    ResponseEntity<ClienteResponseDTO> criarCliente(@Valid @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.criarCliente(dto));
    }

    @Operation(summary = "Lista todos os clientes")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    ResponseEntity<List<ClienteResponseDTO>> listarTodosClientes() {
        return ResponseEntity.ok(clienteService.listarTodosClientes());
    }

    @Operation(summary = "Lista clientes com filtros e paginação")
    @ApiResponse(responseCode = "200", description = "Lista paginada retornada com sucesso")
    @GetMapping("/all-filtered/{page}/{size}")
    public ResponseEntity<Page<ClienteResponseDTO>> getAllFiltered(
            @Parameter(description = "Número da página") @PathVariable int page,
            @Parameter(description = "Tamanho da página") @PathVariable int size,
            @Parameter(description = "Filtros opcionais") ClienteFilter filter) {
        return ResponseEntity.ok(clienteService.allPagedFiltred(page, size, filter));
    }

    @Operation(summary = "Busca cliente pelo ID")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @GetMapping("/{id}")
    ResponseEntity<ClienteResponseDTO> buscarClientePeloId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarClientePeloId(id));
    }

    @Operation(summary = "Atualiza cliente pelo ID")
    @ApiResponse(responseCode = "200", description = "Cliente atualizado")
    @PutMapping("/{id}")
    ResponseEntity<ClienteResponseDTO> atualizarClientePeloId(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.ok(clienteService.atualizarClientePeloId(id, dto));
    }

    @Operation(summary = "Deleta cliente pelo ID")
    @ApiResponse(responseCode = "204", description = "Cliente deletado")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletarClientePeloId(@PathVariable Long id) {
        clienteService.deletarClientePeloId(id);
        return ResponseEntity.noContent().build();
    }
}
