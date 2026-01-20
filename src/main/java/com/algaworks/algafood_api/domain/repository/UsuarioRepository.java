package com.algaworks.algafood_api.domain.repository;

import java.util.List;

import com.algaworks.algafood_api.domain.model.Usuario;

public interface UsuarioRepository {
    
    List<Usuario> getAll();

    Usuario get(long id);

    Usuario add(Usuario usuario);

    void remove(Usuario usuario);

}
