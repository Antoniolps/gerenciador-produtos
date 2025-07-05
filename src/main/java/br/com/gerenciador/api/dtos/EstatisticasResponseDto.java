package br.com.gerenciador.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record EstatisticasResponseDto(
        @Schema(description = "Quantidade total de fornecedores cadastrados", example = "100")
        Long totalFornecedores,

        @Schema(description = "Quantidade total de produtos cadastrados", example = "1500")
        Long totalProdutos,

        @Schema(description = "Quantidade total de clientes cadastrados", example = "2000")
        Long totalClientes
) {}
