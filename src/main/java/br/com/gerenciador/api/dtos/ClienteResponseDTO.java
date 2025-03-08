package br.com.gerenciador.api.dtos;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        EnderecoDTO endereco
) {}
