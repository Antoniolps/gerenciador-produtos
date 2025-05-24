package br.com.gerenciador.api.repositories.spec;

import br.com.gerenciador.api.dtos.filters.ProdutoFilter;
import br.com.gerenciador.api.models.Produto;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.criteria.Predicate;

public class ProdutoSpec {

    public static Specification<Produto> filters(ProdutoFilter filter){
        return(root, query, criteriaBuilder)->{
            List<Predicate> predicates = new ArrayList<>();

            if(Objects.nonNull(filter.nome())){
                if(!filter.nome().isBlank()){
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%"+filter.nome().toLowerCase()+"%"));
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
