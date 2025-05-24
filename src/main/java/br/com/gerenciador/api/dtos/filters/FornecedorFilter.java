package br.com.gerenciador.api.dtos.filters;

import br.com.gerenciador.api.enums.TipoFornecedorEnum;

public record FornecedorFilter(
        String nome,
        TipoFornecedorEnum tipoFornecedor
) {
}
