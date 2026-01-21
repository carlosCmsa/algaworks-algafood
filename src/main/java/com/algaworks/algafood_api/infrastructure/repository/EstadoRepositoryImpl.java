package com.algaworks.algafood_api.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood_api.domain.model.Estado;
import com.algaworks.algafood_api.domain.repository.EstadoRepository;

import jakarta.persistence.EntityManager;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Estado> listar() {
        return entityManager.createQuery("from Estado", Estado.class).getResultList();
    }

    @Override
    public Optional<Estado> buscar(long id) {
        return Optional.ofNullable(entityManager.find(Estado.class, id));
    }

    @Override
    public Estado adicionar(Estado estado) {
        return entityManager.merge(estado);
    }

    @Override
    public void remover(Estado estado) {
        entityManager.remove(estado);
    }

}
