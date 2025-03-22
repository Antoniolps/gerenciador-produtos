package br.com.gerenciador.api.dtos;

public record AuthRequestDTO(
        String username,
        String password
) {
}
