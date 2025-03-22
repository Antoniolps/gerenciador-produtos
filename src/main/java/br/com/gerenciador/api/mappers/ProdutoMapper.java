package br.com.gerenciador.api.mappers;

import br.com.gerenciador.api.dtos.ProdutoRequestDTO;
import br.com.gerenciador.api.dtos.ProdutoResponseDTO;
import br.com.gerenciador.api.models.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mapping(target = "fornecedor", ignore = true)
    Produto toEntity(ProdutoRequestDTO produtoRequestDto);

    ProdutoResponseDTO toDto(Produto produto);
}
