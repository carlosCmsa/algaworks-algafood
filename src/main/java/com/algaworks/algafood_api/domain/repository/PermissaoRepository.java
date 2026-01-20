package com.algaworks.algafood_api.domain.repository;

import java.util.List;

import com.algaworks.algafood_api.domain.model.Permissao;

public interface PermissaoRepository {
    
    List<Permissao> getAll();

    Permissao get(long id);

    Permissao add(Permissao permissao);

    void remove(Permissao permissao);

}
