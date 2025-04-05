package br.com.gerenciador.api.dtos;

import br.com.gerenciador.api.enums.TipoFornecedorEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record FornecedorRequestDTO(
        @NotBlank(message = "O nome do fornecedor é obrigatório")
        @Size(max = 100)
        String nome,

        @Email(message = "Email inválido")
        String email,

        @CNPJ(message = "CNPJ inválido")
        String cnpj,

        @NotNull(message = "O tipo do fornecedor é obrigatório")
        TipoFornecedorEnum tipoFornecedor,

        @Valid
        EnderecoDTO endereco
) {}
