package br.com.gerenciador.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@Schema(description = "DTO para requisição de criação/atualização de Cliente")
public record ClienteRequestDTO(

        @NotBlank(message = "O nome do fornecedor é obrigatório")
        @Schema(description = "Nome completo do cliente", example = "João Silva")
        String nome,

        @CPF(message = "CPF inválido")
        @Schema(description = "CPF do cliente", example = "123.456.789-09")
        String cpf,

        @Email(message = "E-mail inválido")
        @Schema(description = "Email do cliente", example = "joao.silva@email.com")
        String email,

        @Valid
        @Schema(description = "Endereço do cliente")
        EnderecoDTO endereco

) {}
