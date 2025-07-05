package br.com.gerenciador.api.controllers;

import br.com.gerenciador.api.dtos.FornecedorRequestDTO;
import br.com.gerenciador.api.dtos.FornecedorResponseDTO;
import br.com.gerenciador.api.dtos.filters.FornecedorFilter;
import br.com.gerenciador.api.services.FornecedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Fornecedores", description = "Operações para gerenciamento de fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @Operation(summary = "Criar um novo fornecedor")
    @ApiResponse(responseCode = "201", description = "Fornecedor criado com sucesso",
            content = @Content(schema = @Schema(implementation = FornecedorResponseDTO.class)))
    @PostMapping
    public ResponseEntity<FornecedorResponseDTO> criarFornecedor(
            @Valid @RequestBody FornecedorRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorService.criarFornecedor(dto));
    }

    @Operation(summary = "Listar todos os fornecedores")
    @ApiResponse(responseCode = "200", description = "Lista de fornecedores",
            content = @Content(schema = @Schema(implementation = FornecedorResponseDTO.class)))
    @GetMapping
    public ResponseEntity<List<FornecedorResponseDTO>> listarTodosFornecedores(){
        return ResponseEntity.ok(fornecedorService.listarTodosFornecedores());
    }

    @Operation(summary = "Listar fornecedores filtrados e paginados")
    @ApiResponse(responseCode = "200", description = "Lista paginada de fornecedores filtrados",
            content = @Content(schema = @Schema(implementation = FornecedorResponseDTO.class)))
    @GetMapping("/all-filtered/{page}/{size}")
    public ResponseEntity<Page<FornecedorResponseDTO>> listarTodosFornecedoresFiltrados(
            @Parameter(description = "Número da página (começa em 0)", example = "0") @PathVariable int page,
            @Parameter(description = "Tamanho da página", example = "10") @PathVariable int size,
            @Parameter(description = "Filtros para a busca") FornecedorFilter filter){
        return ResponseEntity.ok(fornecedorService.allPagedFiltered(page, size, filter));
    }

    @Operation(summary = "Buscar fornecedor por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Fornecedor encontrado",
                    content = @Content(schema = @Schema(implementation = FornecedorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> buscarFornecedorPorId(
            @Parameter(description = "ID do fornecedor", example = "1") @PathVariable Long id){
        return ResponseEntity.ok(fornecedorService.buscarFornecedorPeloId(id));
    }

    @Operation(summary = "Atualizar fornecedor pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Fornecedor atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = FornecedorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> atualizarFornecedorPorId(
            @Parameter(description = "ID do fornecedor", example = "1") @PathVariable Long id,
            @Valid @RequestBody FornecedorRequestDTO dto){
        return ResponseEntity.ok(fornecedorService.atualizarFornecedorPeloId(id, dto));
    }

    @Operation(summary = "Deletar fornecedor pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Fornecedor deletado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarFornecedorPorId(
            @Parameter(description = "ID do fornecedor", example = "1") @PathVariable Long id){
        fornecedorService.deletarFornecedorPeloId(id);
        return ResponseEntity.noContent().build();
    }

}
