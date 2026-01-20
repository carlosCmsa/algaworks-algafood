package com.algaworks.algafood_api.domain.repository;

import java.util.List;

import com.algaworks.algafood_api.domain.model.Restaurante;

public interface RestauranteRepository {
    
    List<Restaurante> listar();

    Restaurante buscar(long id);

    Restaurante adicionar(Restaurante cozinha);

    void remover(Restaurante cozinha);

}
