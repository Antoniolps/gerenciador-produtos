package br.com.gerenciador.api.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequestDTO(
        @NotBlank(message = "O nome do fornecedor é obrigatório")
        String nome,

        @CPF(message = "CPF inválido")
        String cpf,

        @Email(message = "E-mail inválido")
        String email,

        @Valid
        EnderecoDTO endereco
) {}
