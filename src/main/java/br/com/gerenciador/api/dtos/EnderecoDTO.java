package br.com.gerenciador.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(
        @NotBlank(message = "O logradouro é obrigatório")
        @Size(max = 150)
        String logradouro,

        @NotBlank(message = "O número é obrigatório")
        @Size(max = 10)
        String numero,

        @Size(max = 50)
        String complemento,

        @NotBlank(message = "O bairro é obrigatório")
        @Size(max = 50)
        String bairro,

        @NotBlank(message = "A cidade é obrigatória")
        @Size(max = 50)
        String cidade,

        @NotBlank(message = "O estado é obrigatório")
        @Size(max = 50)
        String estado,

        @NotBlank(message = "O país é obrigatório")
        @Size(max = 50)
        String pais,

        @NotBlank(message = "O CEP é obrigatório")
        @Size(max = 20)
        String cep
) {}
