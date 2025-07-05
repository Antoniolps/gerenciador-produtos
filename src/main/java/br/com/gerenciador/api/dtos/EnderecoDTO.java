package br.com.gerenciador.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO representando endereço")
public record EnderecoDTO(

        @NotBlank(message = "O logradouro é obrigatório")
        @Size(max = 150)
        @Schema(description = "Logradouro do endereço", example = "Rua das Flores")
        String logradouro,

        @NotBlank(message = "O número é obrigatório")
        @Size(max = 10)
        @Schema(description = "Número do endereço", example = "123")
        String numero,

        @Size(max = 50)
        @Schema(description = "Complemento do endereço", example = "Apto 45")
        String complemento,

        @NotBlank(message = "O bairro é obrigatório")
        @Size(max = 50)
        @Schema(description = "Bairro do endereço", example = "Centro")
        String bairro,

        @NotBlank(message = "A cidade é obrigatória")
        @Size(max = 50)
        @Schema(description = "Cidade do endereço", example = "São Paulo")
        String cidade,

        @NotBlank(message = "O estado é obrigatório")
        @Size(max = 50)
        @Schema(description = "Estado do endereço", example = "SP")
        String estado,

        @NotBlank(message = "O país é obrigatório")
        @Size(max = 50)
        @Schema(description = "País do endereço", example = "Brasil")
        String pais,

        @NotBlank(message = "O CEP é obrigatório")
        @Size(max = 20)
        @Schema(description = "CEP do endereço", example = "01001-000")
        String cep
) {}
