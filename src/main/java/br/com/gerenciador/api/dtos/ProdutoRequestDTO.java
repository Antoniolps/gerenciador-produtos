package br.com.gerenciador.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Schema(description = "Nome do produto", example = "Camiseta Polo")
        String nome,

        @NotNull(message = "Preço é obrigatório")
        @DecimalMin(value = "0.0", message = "Preço deve ser maior que zero")
        @Schema(description = "Preço do produto", example = "79.90")
        BigDecimal preco,

        @Schema(description = "Descrição do produto", example = "Camiseta de algodão, tamanho M")
        String descricao,

        @NotNull(message = "Quantidade em estoque é obrigatória")
        @Min(value = 0, message = "Quantidade não pode ser negativa")
        @Schema(description = "Quantidade disponível no estoque", example = "10")
        Integer quantidadeEstoque,

        @NotNull(message = "ID do Fornecedor é obrigatório")
        @Schema(description = "ID do fornecedor do produto", example = "5")
        Long fornecedorId

) {
}
