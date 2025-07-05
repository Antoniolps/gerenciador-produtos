package br.com.gerenciador.api.dtos;

import br.com.gerenciador.api.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

public record RegisterRequestDTO(
        @NotBlank(message = "Nome de usuário é obrigatório")
        @Schema(description = "Nome de usuário para login", example = "usuario123")
        String username,

        @NotBlank(message = "Senha é obrigatória")
        @Schema(description = "Senha do usuário", example = "senhaSegura123!")
        String password,

        @Email(message = "Email inválido")
        @NotBlank(message = "Email é obrigatório")
        @Schema(description = "Email do usuário", example = "usuario@example.com")
        String email,

        @Schema(description = "Perfil do usuário", example = "ROLE_USER", allowableValues = {"ROLE_USER", "ROLE_ADMIN"})
        Role role
) {}
