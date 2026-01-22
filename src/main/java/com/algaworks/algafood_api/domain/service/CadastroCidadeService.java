package com.algaworks.algafood_api.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood_api.domain.model.Cidade;
import com.algaworks.algafood_api.domain.model.Estado;
import com.algaworks.algafood_api.domain.repository.CidadeRepository;
import com.algaworks.algafood_api.domain.repository.EstadoRepository;

import jakarta.persistence.NoResultException;

@Service
public class CadastroCidadeService {
    
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade buscar(long id) {
        return cidadeRepository.findById(id).orElseThrow(
            () -> new NoResultException("Não foi possível encontrar nenhuma cidade para o identificador informado."));
    }

    @Transactional
    public Cidade adicionar(Cidade cidade) {
        Estado estado = estadoRepository.findById(cidade.getEstado().getId()).orElseThrow(
            () -> new NoResultException("Não foi possível encontrar nenhum estado para o identificador informado."));
        
        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    @Transactional
    public Cidade atualizar(long id, Cidade cidade) {
        Cidade cidadeAtual = this.buscar(id);

        BeanUtils.copyProperties(cidade, cidadeAtual, "id", "estado");

        return cidadeRepository.save(cidadeAtual);
    }

    @Transactional
    public void remover(long id) {
        Cidade cidade = this.buscar(id);

        try {
            cidadeRepository.delete(cidade);
        } catch(DataIntegrityViolationException e) {
            throw e;
        }

    }

}
