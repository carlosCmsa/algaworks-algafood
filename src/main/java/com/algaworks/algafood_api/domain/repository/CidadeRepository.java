package com.algaworks.algafood_api.domain.repository;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood_api.domain.model.Cidade;

public interface CidadeRepository {
    
    List<Cidade> listar();

    Optional<Cidade> buscar(long id);

    Cidade adicionar(Cidade cidade);

    void remover(Cidade id);

}
