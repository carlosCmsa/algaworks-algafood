package com.algaworks.algafood_api.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.repository.RestauranteRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Restaurante> listar() {
        return entityManager.createQuery("from Restaurante", Restaurante.class).getResultList();
    }

    @Override
    public Restaurante buscar(long id) {
        return entityManager.find(Restaurante.class, id);
    }

    @Override
    public Restaurante adicionar(Restaurante restaurante) {
        return entityManager.merge(restaurante);
    }

    @Override
    public void remover(Restaurante restaurante) {
        Restaurante rt = buscar(restaurante.getId());
        entityManager.remove(rt);
    }

}
