package br.com.gerenciador.api.controllers;

import br.com.gerenciador.api.dtos.ProdutoRequestDTO;
import br.com.gerenciador.api.dtos.ProdutoResponseDTO;
import br.com.gerenciador.api.dtos.filters.ProdutoFilter;
import br.com.gerenciador.api.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Produtos", description = "Operações para gerenciamento de produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Operation(summary = "Criar um novo produto")
    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso",
            content = @Content(schema = @Schema(implementation = ProdutoResponseDTO.class)))
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criarProduto(
            @Valid @RequestBody ProdutoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criarProduto(dto));
    }

    @Operation(summary = "Buscar produto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto encontrado",
                    content = @Content(schema = @Schema(implementation = ProdutoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarProdutoPorId(
            @Parameter(description = "ID do produto", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorId(id));
    }

    @Operation(summary = "Listar todos os produtos")
    @ApiResponse(responseCode = "200", description = "Lista de produtos",
            content = @Content(schema = @Schema(implementation = ProdutoResponseDTO.class)))
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> buscarProdutos() {
        return ResponseEntity.ok(produtoService.buscarProdutos());
    }

    @Operation(summary = "Listar produtos com filtros e paginação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista paginada de produtos filtrados",
                    content = @Content(schema = @Schema(implementation = ProdutoResponseDTO.class)))
    })
    @GetMapping("/allFiltered/{page}/{size}")
    public ResponseEntity<Page<ProdutoResponseDTO>> buscarProdutosFiltrados(
            @Parameter(description = "Número da página (começa em 0)", example = "0") @PathVariable int page,
            @Parameter(description = "Tamanho da página", example = "10") @PathVariable int size,
            @Parameter(description = "Filtros para a busca") ProdutoFilter filter
    ) {
        return ResponseEntity.ok(produtoService.allPagedFiltered(page, size, filter));
    }

    @Operation(summary = "Atualizar produto existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = ProdutoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(
            @Parameter(description = "ID do produto", example = "1") @PathVariable Long id,
            @Valid @RequestBody ProdutoRequestDTO dto) {
        return ResponseEntity.ok(produtoService.atualizarProduto(id, dto));
    }

    @Operation(summary = "Deletar produto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(
            @Parameter(description = "ID do produto", example = "1") @PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

}
