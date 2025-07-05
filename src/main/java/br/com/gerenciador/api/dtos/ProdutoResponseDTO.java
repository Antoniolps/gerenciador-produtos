package br.com.gerenciador.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "DTO de resposta com dados do produto")
public record ProdutoResponseDTO(

        @Schema(description = "ID do produto", example = "1")
        Long id,

        @Schema(description = "Nome do produto", example = "Camiseta Polo")
        String nome,

        @Schema(description = "Preço do produto", example = "79.90")
        BigDecimal preco,

        @Schema(description = "Descrição do produto", example = "Camiseta de algodão, tamanho M")
        String descricao,

        @Schema(description = "Quantidade disponível no estoque", example = "10")
        Integer quantidadeEstoque,

        @Schema(description = "Dados do fornecedor do produto")
        FornecedorResponseDTO fornecedor

) {
}
