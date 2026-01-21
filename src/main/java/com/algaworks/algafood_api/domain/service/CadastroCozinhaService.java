package com.algaworks.algafood_api.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;

import jakarta.persistence.NoResultException;

@Service
public class CadastroCozinhaService {
    
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    public Cozinha buscar(long id) {
        Cozinha cozinha = cozinhaRepository.findById(id).orElseThrow(() -> 
            new NoResultException("Não foi possível encontrar nenhuma cozinha para o identificador informado."));

        return cozinha;
    }

    @Transactional
    public Cozinha adicionar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public Cozinha atualizar(long id, Cozinha cozinha) {
        Cozinha cozinhaAtual = this.buscar(id);

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        return cozinhaRepository.save(cozinhaAtual);
    }

    @Transactional
    public void remover(long id) {
        Cozinha cozinha = this.buscar(id);

        try {
            cozinhaRepository.delete(cozinha);
        } catch(DataIntegrityViolationException e) {
            throw e;
        }

    }

}
