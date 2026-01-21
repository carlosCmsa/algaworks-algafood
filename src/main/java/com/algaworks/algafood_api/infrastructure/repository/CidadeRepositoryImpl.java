package com.algaworks.algafood_api.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood_api.domain.model.Cidade;
import com.algaworks.algafood_api.domain.repository.CidadeRepository;

import jakarta.persistence.EntityManager;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Cidade> listar() {
        return entityManager.createQuery("from Cidade", Cidade.class).getResultList();
    }

    @Override
    public Optional<Cidade> buscar(long id) {
        return Optional.ofNullable(entityManager.find(Cidade.class, id));
    }

    @Override
    public Cidade adicionar(Cidade cidade) {
        return entityManager.merge(cidade);
    }

    @Override
    public void remover(Cidade cidade) {
        entityManager.remove(cidade);
    }
    
}
