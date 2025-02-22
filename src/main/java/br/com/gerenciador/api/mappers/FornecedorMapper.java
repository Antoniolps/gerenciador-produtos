package br.com.gerenciador.api.mappers;

import br.com.gerenciador.api.dtos.FornecedorRequestDTO;
import br.com.gerenciador.api.dtos.FornecedorResponseDTO;
import br.com.gerenciador.api.models.Fornecedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FornecedorMapper {
    // Conversor de DTO para entidade
    @Mapping(target = "id", ignore = true)
    Fornecedor toEntity(FornecedorRequestDTO dto);

    // Conversor de entidade para DTO
    FornecedorResponseDTO toDTO(Fornecedor fornecedor);

}
