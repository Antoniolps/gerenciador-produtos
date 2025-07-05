package br.com.gerenciador.api.dtos;

import br.com.gerenciador.api.enums.Role;

import io.swagger.v3.oas.annotations.media.Schema;

public record UsuarioResponseDTO(
        @Schema(description = "Nome de usuário", example = "usuario123")
        String username,

        @Schema(description = "Email do usuário", example = "usuario@example.com")
        String email,

        @Schema(description = "Perfil do usuário", example = "ROLE_USER")
        Role role
) {}

