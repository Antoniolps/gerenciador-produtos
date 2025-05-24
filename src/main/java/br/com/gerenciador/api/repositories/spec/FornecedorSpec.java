package br.com.gerenciador.api.repositories.spec;

import br.com.gerenciador.api.dtos.filters.FornecedorFilter;
import br.com.gerenciador.api.models.Fornecedor;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FornecedorSpec {

    public static Specification<Fornecedor> filters(FornecedorFilter filter){
        return (root, query, criteriaBuilder)->{
            List<Predicate> predicates = new ArrayList<Predicate>();

            if(Objects.nonNull(filter.nome())){
                if(!filter.nome().isBlank()){
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%"+filter.nome().toLowerCase()+"%"));
                }
            }

            if(Objects.nonNull(filter.tipoFornecedor())){
                predicates.add(criteriaBuilder.equal(root.get("tipoFornecedor"), filter.tipoFornecedor()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}
