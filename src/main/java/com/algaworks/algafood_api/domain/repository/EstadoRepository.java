package com.algaworks.algafood_api.domain.repository;

import java.util.List;

import com.algaworks.algafood_api.domain.model.Estado;

public interface EstadoRepository {
    
    List<Estado> listar();

    Estado buscar(long id);

    Estado adicionar(Estado estado);

    void remover(Estado estado);

}
