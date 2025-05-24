package br.com.gerenciador.api.services;

import br.com.gerenciador.api.dtos.FornecedorRequestDTO;
import br.com.gerenciador.api.dtos.FornecedorResponseDTO;
import br.com.gerenciador.api.dtos.filters.FornecedorFilter;
import br.com.gerenciador.api.mappers.EnderecoMapper;
import br.com.gerenciador.api.mappers.FornecedorMapper;
import br.com.gerenciador.api.models.Fornecedor;
import br.com.gerenciador.api.repositories.FornecedorRepository;
import br.com.gerenciador.api.repositories.spec.FornecedorSpec;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @Override
    public List<FornecedorResponseDTO> listarTodosFornecedores() {
        return fornecedorRepository.findAll().stream().map(fornecedorMapper::toDTO).toList();
    }

    @Override
    public Page<FornecedorResponseDTO> allPagedFiltered(int page, int size, FornecedorFilter filter) {
        Pageable pageable = PageRequest.of(page, size);
        return fornecedorRepository.findAll(FornecedorSpec.filters(filter), pageable)
                .map(fornecedorMapper::toDTO);
    }

    @Override
    public FornecedorResponseDTO buscarFornecedorPeloId(Long id) {
        return fornecedorRepository.findById(id).map(fornecedorMapper::toDTO).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado")
        );
    }

    //Esta anotação garante que se houver erro durante a operação, nenhuma alteração parcial aconteça
    @Transactional
    @Override
    public FornecedorResponseDTO atualizarFornecedorPeloId(Long id, FornecedorRequestDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado")
        );

        fornecedor.setNome(dto.nome());
        fornecedor.setEmail(dto.email());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setTipoFornecedor(dto.tipoFornecedor());
        fornecedor.setEndereco(enderecoMapper.toEntity(dto.endereco()));

        return fornecedorMapper.toDTO(fornecedorRepository.save(fornecedor));
    }

    @Transactional
    @Override
    public void deletarFornecedorPeloId(Long id) {
        if(!fornecedorRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado");
        }

        fornecedorRepository.deleteById(id);
    }


}
