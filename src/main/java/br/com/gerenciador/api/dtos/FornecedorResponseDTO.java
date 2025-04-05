package br.com.gerenciador.api.dtos;

import br.com.gerenciador.api.enums.TipoFornecedorEnum;

public record FornecedorResponseDTO(
        Long id,
        String nome,
        String email,
        String cnpj,
        TipoFornecedorEnum tipoFornecedor,
        EnderecoDTO endereco
) {}
