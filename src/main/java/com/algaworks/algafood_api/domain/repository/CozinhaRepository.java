package com.algaworks.algafood_api.domain.repository;

import java.util.List;

import com.algaworks.algafood_api.domain.model.Cozinha;

public interface CozinhaRepository {
    
    List<Cozinha> listar();

    Cozinha buscar(long id);

    Cozinha adicionar(Cozinha cozinha);

    void remover(Cozinha cozinha);

}
