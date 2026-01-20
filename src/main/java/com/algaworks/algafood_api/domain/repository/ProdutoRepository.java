package com.algaworks.algafood_api.domain.repository;

import java.util.List;

import com.algaworks.algafood_api.domain.model.Produto;

public interface ProdutoRepository {
    
    List<Produto> getAll();

    Produto get(long id);

    Produto add(Produto produto);

    void remove(Produto produto);

}
