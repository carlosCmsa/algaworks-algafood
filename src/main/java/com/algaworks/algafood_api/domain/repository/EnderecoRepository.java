package com.algaworks.algafood_api.domain.repository;

import java.util.List;

import com.algaworks.algafood_api.domain.model.Endereco;

public interface EnderecoRepository {
    
    List<Endereco> getAll();

    Endereco get(long id);

    Endereco add(Endereco endereco);

    void remove(Endereco endereco);

}
