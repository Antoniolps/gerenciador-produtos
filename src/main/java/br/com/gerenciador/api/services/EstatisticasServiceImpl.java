package br.com.gerenciador.api.services;


import br.com.gerenciador.api.dtos.EstatisticasResponseDto;
import br.com.gerenciador.api.repositories.ClienteRepository;
import br.com.gerenciador.api.repositories.FornecedorRepository;
import br.com.gerenciador.api.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstatisticasServiceImpl implements EstatisticasService {

    private final FornecedorRepository fornecedorRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public EstatisticasResponseDto getEstatisticas() {
        Long totalFornecedores = fornecedorRepository.count();
        Long totalProdutos = produtoRepository.count();
        Long totalClientes = clienteRepository.count();

        return new EstatisticasResponseDto(totalFornecedores, totalProdutos, totalClientes);
    }
}
