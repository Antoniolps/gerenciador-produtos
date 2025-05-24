package br.com.gerenciador.api.controllers;

import br.com.gerenciador.api.dtos.FornecedorRequestDTO;
import br.com.gerenciador.api.dtos.FornecedorResponseDTO;
import br.com.gerenciador.api.dtos.filters.FornecedorFilter;
import br.com.gerenciador.api.services.FornecedorService;
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
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<FornecedorResponseDTO> criarFornecedor(@Valid @RequestBody FornecedorRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorService.criarFornecedor(dto));
    }

    @GetMapping
    public ResponseEntity<List<FornecedorResponseDTO>> listarTodosFornecedores(){
        return ResponseEntity.ok(fornecedorService.listarTodosFornecedores());
    }

    @GetMapping("/all-filtered/{page}/{size}")
    public ResponseEntity<Page<FornecedorResponseDTO>> listarTodosFornecedoresFiltrados(
            @PathVariable int page,
            @PathVariable int size,
            FornecedorFilter filter){
        return ResponseEntity.ok(fornecedorService.allPagedFiltered(page, size, filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> buscarFornecedorPorId(@PathVariable Long id){
        return ResponseEntity.ok(fornecedorService.buscarFornecedorPeloId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> atualizarFornecedorPorId(@PathVariable Long id, @Valid @RequestBody FornecedorRequestDTO dto){
        return ResponseEntity.ok(fornecedorService.atualizarFornecedorPeloId(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarFornecedorPorId(@PathVariable Long id){
        fornecedorService.deletarFornecedorPeloId(id);
        return ResponseEntity.noContent().build();
    }

}
