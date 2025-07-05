package br.com.gerenciador.api.dtos;
import io.swagger.v3.oas.annotations.media.Schema;

public record AuthRequestDTO(
        @Schema(description = "Nome de usuário para login", example = "usuario123", required = true)
        String username,

        @Schema(description = "Senha do usuário", example = "SenhaSegura123!", required = true)
        String password
) {}
