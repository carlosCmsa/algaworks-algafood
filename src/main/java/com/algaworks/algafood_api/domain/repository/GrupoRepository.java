package com.algaworks.algafood_api.domain.repository;

import java.util.List;

import com.algaworks.algafood_api.domain.model.Grupo;

public interface GrupoRepository {
    
    List<Grupo> getAll();

    Grupo get(long id);

    Grupo add(Grupo grupo);

    void remove(Grupo grupo);

}
