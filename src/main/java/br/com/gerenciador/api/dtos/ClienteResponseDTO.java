package br.com.gerenciador.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de resposta contendo dados do Cliente")
public record ClienteResponseDTO(

        @Schema(description = "ID do cliente", example = "1")
        Long id,

        @Schema(description = "Nome completo do cliente", example = "João Silva")
        String nome,

        @Schema(description = "CPF do cliente", example = "123.456.789-09")
        String cpf,

        @Schema(description = "Email do cliente", example = "joao.silva@email.com")
        String email,

        @Schema(description = "Endereço do cliente")
        EnderecoDTO endereco

) {}
