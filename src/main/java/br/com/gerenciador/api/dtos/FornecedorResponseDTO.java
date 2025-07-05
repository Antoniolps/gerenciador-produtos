package br.com.gerenciador.api.dtos;

import br.com.gerenciador.api.enums.TipoFornecedorEnum;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de resposta com dados do fornecedor")
public record FornecedorResponseDTO(

        @Schema(description = "ID do fornecedor", example = "1")
        Long id,

        @Schema(description = "Nome do fornecedor", example = "Fornecedor ABC Ltda")
        String nome,

        @Schema(description = "Email de contato do fornecedor", example = "contato@fornecedor.com")
        String email,

        @Schema(description = "CNPJ do fornecedor", example = "12.345.678/0001-99")
        String cnpj,

        @Schema(description = "Tipo do fornecedor", example = "PESSOA_JURIDICA")
        TipoFornecedorEnum tipoFornecedor,

        @Schema(description = "Endereço do fornecedor")
        EnderecoDTO endereco

) {}
