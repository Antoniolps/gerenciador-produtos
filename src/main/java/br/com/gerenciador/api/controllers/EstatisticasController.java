package br.com.gerenciador.api.controllers;

import br.com.gerenciador.api.dtos.EstatisticasResponseDto;
import br.com.gerenciador.api.repositories.ClienteRepository;
import br.com.gerenciador.api.repositories.FornecedorRepository;
import br.com.gerenciador.api.repositories.ProdutoRepository;
import br.com.gerenciador.api.services.EstatisticasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatisticas")
@RequiredArgsConstructor
public class EstatisticasController {

    private final EstatisticasService estatisticasService;

    @GetMapping
    public ResponseEntity<EstatisticasResponseDto> getEstatisticas() {
        return ResponseEntity.ok(estatisticasService.getEstatisticas());
    }

}
