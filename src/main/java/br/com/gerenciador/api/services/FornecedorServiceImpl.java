package br.com.gerenciador.api.services;

import br.com.gerenciador.api.dtos.FornecedorRequestDTO;
import br.com.gerenciador.api.dtos.FornecedorResponseDTO;
import br.com.gerenciador.api.mappers.EnderecoMapper;
import br.com.gerenciador.api.mappers.FornecedorMapper;
import br.com.gerenciador.api.models.Fornecedor;
import br.com.gerenciador.api.repositories.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FornecedorServiceImpl implements FornecedorService{

    private final FornecedorRepository fornecedorRepository;
    private final FornecedorMapper fornecedorMapper;
    private final EnderecoMapper enderecoMapper;

    @Override
    public FornecedorResponseDTO criarFornecedor(FornecedorRequestDTO dto) {
        //Converte o DTO para Entidade
        Fornecedor fornecedor = fornecedorMapper.toEntity(dto);

        return fornecedorMapper.toDTO(fornecedorRepository.save(fornecedor));
    }


}
