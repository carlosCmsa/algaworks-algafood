package com.algaworks.algafood_api.domain.repository;

import java.util.List;

import com.algaworks.algafood_api.domain.model.Cidade;

public interface CidadeRepository {
    
    List<Cidade> getAll();

    Cidade get(long id);

    Cidade add(Cidade cidade);

    void remove(Cidade cidade);

}
