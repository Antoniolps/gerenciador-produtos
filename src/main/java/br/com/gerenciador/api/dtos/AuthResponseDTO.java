package br.com.gerenciador.api.dtos;
import io.swagger.v3.oas.annotations.media.Schema;

public record AuthResponseDTO(
        @Schema(description = "Token JWT de autenticação gerado após login ou registro", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String token
) {}

