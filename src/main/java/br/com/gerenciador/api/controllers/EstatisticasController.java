package br.com.gerenciador.api.controllers;

import br.com.gerenciador.api.dtos.EstatisticasResponseDto;
import br.com.gerenciador.api.services.EstatisticasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatisticas")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Tag(name = "Estatísticas", description = "Endpoints para obter estatísticas do sistema")
public class EstatisticasController {

    private final EstatisticasService estatisticasService;

    @Operation(summary = "Obter estatísticas gerais do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estatísticas retornadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public ResponseEntity<EstatisticasResponseDto> getEstatisticas() {
        return ResponseEntity.ok(estatisticasService.getEstatisticas());
    }
}
