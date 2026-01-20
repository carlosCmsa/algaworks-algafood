package com.algaworks.algafood_api.domain.repository;

import java.util.List;

import com.algaworks.algafood_api.domain.model.Pedido;

public interface PedidoRepository {
    
    List<Pedido> getAll();

    Pedido get(long id);

    Pedido add(Pedido pedido);

    void remove(Pedido pedido);

}
