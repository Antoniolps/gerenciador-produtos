package br.com.gerenciador.api.dtos;

import br.com.gerenciador.api.enums.Role;

public record UsuarioResponseDTO(
        String username,
        String email,
        Role role

) {
}
