package br.com.gerenciador.api.mappers;

import br.com.gerenciador.api.dtos.EnderecoDTO;
import br.com.gerenciador.api.models.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toEntity(EnderecoDTO dto);

    EnderecoDTO toDTO(Endereco endereco);
}
