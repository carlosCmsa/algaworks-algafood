package com.algaworks.algafood_api.domain.repository;

import java.util.List;

import com.algaworks.algafood_api.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {
    
    List<FormaPagamento> getAll();

    FormaPagamento get(long id);

    FormaPagamento add(FormaPagamento formaPagamento);

    void remove(FormaPagamento formaPagamento);

}
