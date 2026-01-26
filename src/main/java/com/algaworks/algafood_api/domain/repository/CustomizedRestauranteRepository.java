package com.algaworks.algafood_api.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood_api.domain.model.Restaurante;

@Repository
public interface CustomizedRestauranteRepository {
    
    public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);

    public List<Restaurante> filter(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal, Boolean ativo, Boolean aberto);

    public List<Restaurante> filterByCriteria(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal, Boolean ativo, Boolean aberto);

}
