package br.com.gerenciador.api.dtos;

import br.com.gerenciador.api.enums.TipoFornecedorEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record FornecedorRequestDTO(

        @NotBlank(message = "O nome do fornecedor é obrigatório")
        @Size(max = 100)
        @Schema(description = "Nome do fornecedor", example = "Fornecedor ABC Ltda")
        String nome,

        @Email(message = "Email inválido")
        @Schema(description = "Email de contato do fornecedor", example = "contato@fornecedor.com")
        String email,

        @CNPJ(message = "CNPJ inválido")
        @Schema(description = "CNPJ do fornecedor", example = "12.345.678/0001-99")
        String cnpj,

        @NotNull(message = "O tipo do fornecedor é obrigatório")
        @Schema(description = "Tipo do fornecedor", example = "PESSOA_JURIDICA")
        TipoFornecedorEnum tipoFornecedor,

        @Valid
        @Schema(description = "Endereço do fornecedor")
        EnderecoDTO endereco

) {}
