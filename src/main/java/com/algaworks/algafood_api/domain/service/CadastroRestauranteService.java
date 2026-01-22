package com.algaworks.algafood_api.domain.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.repository.RestauranteRepository;

import jakarta.persistence.NoResultException;
import tools.jackson.databind.ObjectMapper;

@Service
public class CadastroRestauranteService {
    
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscar(long id) {
        Restaurante restaurante = restauranteRepository.findById(id).orElseThrow(() ->
            new NoResultException("Não foi possível encontrar nenhum restaurante para o identificador informado."));

        return restaurante;

    }

    @Transactional
    public Restaurante adicionar(Restaurante restaurante) {
        cadastroCozinhaService.buscar(restaurante.getCozinha().getId());

        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public Restaurante atualizar(long id, Restaurante restaurante) {
        Restaurante restauranteAtual = this.buscar(id);

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "cozinha");

        return this.adicionar(restauranteAtual);
    }

    @Transactional
    public Restaurante atualizar(long id, Map<String, Object> campos) {
        Restaurante restaurante = this.buscar(id);

        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante novosValores = objectMapper.convertValue(campos, Restaurante.class);

        campos.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, k);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, novosValores);
            
            ReflectionUtils.setField(field, restaurante, novoValor);
        });

        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void remover(long id) {
        Restaurante restaurante = this.buscar(id);

        try {
            restauranteRepository.delete(restaurante);
        } catch(DataIntegrityViolationException e) {
            throw e;
        }
        
    }

}
