package com.algaworks.algafood_api.domain.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;
import com.algaworks.algafood_api.domain.repository.RestauranteRepository;

import jakarta.persistence.NoResultException;

@Service
public class CadastroRestauranteService {
    
    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    public Restaurante buscar(long id) {
        Restaurante restaurante = restauranteRepository.buscar(id);

        if(Objects.isNull(restaurante)) {
            throw new NoResultException("Não foi possível encontrar nenhum restaurante para o identificador informado.");
        }

        return restaurante;

    }

    public Restaurante adicionar(Restaurante restaurante) {
        Cozinha cozinha = cozinhaRepository.buscar(restaurante.getCozinha().getId());
        
        if(Objects.isNull(cozinha)) {
            throw new NoResultException("Não foi possível encontrar nenhuma cozinha para o identificador informado.");
        }

        return restauranteRepository.adicionar(restaurante);
    }

    public Restaurante atualizar(long id, Restaurante restaurante) {
        Restaurante restauranteAtual = this.buscar(id);

        if(Objects.isNull(restauranteAtual)) {
            throw new NoResultException("Não foi possível encontrar nenhum restaurante para o identificador informado.");
        }

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

        return this.adicionar(restauranteAtual);
    }

    public void remover(long id) {
        Restaurante restaurante = this.buscar(id);

        if(Objects.isNull(restaurante)) {
            throw new NoResultException("Não foi possível encontrar nenhum restaurante para o identificador informado.");
        }

        try {
            restauranteRepository.remover(restaurante);
        } catch(DataIntegrityViolationException e) {
            throw e;
        }
        
    }

}
