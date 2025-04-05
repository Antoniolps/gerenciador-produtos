package br.com.gerenciador.api.dtos;

public record EstatisticasResponseDto(
        Long totalFornecedores,
        Long totalProdutos,
        Long totalClientes
) {}
