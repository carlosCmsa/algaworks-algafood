package com.algaworks.algafood_api.domain.repository;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood_api.domain.model.Restaurante;

public interface RestauranteRepository {
    
    List<Restaurante> listar();

    Optional<Restaurante> buscar(long id);

    Restaurante adicionar(Restaurante restaurante);

    void remover(Restaurante restaurante);

}
