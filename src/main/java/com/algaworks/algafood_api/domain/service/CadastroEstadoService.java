package com.algaworks.algafood_api.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood_api.domain.model.Estado;
import com.algaworks.algafood_api.domain.repository.EstadoRepository;

import jakarta.persistence.NoResultException;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado buscar(long id) {
        return estadoRepository.findById(id).orElseThrow(
            () -> new NoResultException("Não foi possível encontrar nenhum estado para o identificador informado."));
    }

    @Transactional
    public Estado adicionar(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Transactional
    public Estado atualizar(long id, Estado estado) {
        Estado estadoAtual = this.buscar(id);

        BeanUtils.copyProperties(estado, estadoAtual, "id");

        return estadoRepository.save(estadoAtual);
    }

    @Transactional
    public void remover(long id) {
        Estado estado = this.buscar(id);

        try {
            estadoRepository.delete(estado);
        } catch(DataIntegrityViolationException e) {
            throw e; 
        }

    }

}
