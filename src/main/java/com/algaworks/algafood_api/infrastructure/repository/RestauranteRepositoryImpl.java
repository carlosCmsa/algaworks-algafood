package com.algaworks.algafood_api.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.repository.CustomizedRestauranteRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class RestauranteRepositoryImpl implements CustomizedRestauranteRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
        
        final String sql = "from Restaurante where nome like :nome and taxaFrete between :taxaInicial and :taxaFinal";

        return entityManager.createQuery(sql, Restaurante.class)
            .setParameter("nome", "%" + nome + "%")
            .setParameter("taxaInicial", taxaInicial)
            .setParameter("taxaFinal", taxaFinal)
            .getResultList();

    }

    @Override
    public List<Restaurante> filter(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal, Boolean ativo, Boolean aberto) {
        StringBuilder sql = new StringBuilder("from Restaurante where 0 = 0 ");

        Map<String, Object> parameters = new HashMap<>();

        if(StringUtils.hasLength(nome)) {
            sql.append("and nome like :nome ");
            parameters.put("nome", "%" + nome + "%");
        }

        if(Objects.nonNull(taxaInicial)) {
            sql.append("and taxaFrete >= :taxaInicial ");
            parameters.put("taxaInicial", taxaInicial);
        }

        if(Objects.nonNull(taxaFinal)) {
            sql.append("and taxaFrete <= :taxaFinal ");
            parameters.put("taxaFinal", taxaFinal);
        }

        if(Objects.nonNull(ativo)) {
            sql.append("and ativo = :ativo ");
            parameters.put("ativo", ativo);
        }

        if(Objects.nonNull(aberto)) {
            sql.append("and aberto = :aberto ");
            parameters.put("aberto", aberto);
        }

        TypedQuery<Restaurante> query = entityManager.createQuery(sql.toString(), Restaurante.class);
        parameters.forEach((chave, valor) -> query.setParameter(chave, valor));

        return query.getResultList();

    }

    @Override
    public List<Restaurante> filterByCriteria(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal, Boolean ativo, Boolean aberto) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Restaurante> criteriaQuery = builder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteriaQuery.from(Restaurante.class);

        List<Predicate> predicates = new ArrayList<>();

        if(StringUtils.hasLength(nome)) {
            predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
        }

        if(Objects.nonNull(taxaInicial)) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial));
        }

        if(Objects.nonNull(taxaFinal)) {
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFinal));
        }

        if(Objects.nonNull(ativo)) {
            predicates.add(builder.equal(root.get("ativo"), ativo));
        }

        if(Objects.nonNull(aberto)) {
            predicates.add(builder.equal(root.get("aberto"), aberto));
        }
        
        criteriaQuery.where(predicates);

        return entityManager.createQuery(criteriaQuery).getResultList();

    }
    
}
