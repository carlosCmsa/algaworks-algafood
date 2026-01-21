package com.algaworks.algafood_api.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood_api.domain.model.Cidade;
import com.algaworks.algafood_api.domain.service.CadastroCidadeService;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/api/v1/cidades")
public class CidadeController {
    
    @Autowired
    CadastroCidadeService cadastroCidadeService;

    @GetMapping
    public ResponseEntity<List<Cidade>> listar() {
        return ResponseEntity.ok(cadastroCidadeService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscar(@PathVariable long id) {

        try {
            return ResponseEntity.ok(cadastroCidadeService.buscar(id));
        } catch(NoResultException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Cidade> adicionar(@RequestBody Cidade body, HttpServletRequest http) {
        
        try {
            Cidade cidade = cadastroCidadeService.adicionar(body);

            URI uri = URI.create(String.format("%s/%d", http.getRequestURI(), cidade.getId()));

            return ResponseEntity.created(uri).body(cidade);
        } catch(NoResultException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Cidade> atualizar(@PathVariable long id, @RequestBody Cidade body) {

        try {
            return ResponseEntity.ok(cadastroCidadeService.atualizar(id, body));
        } catch(NoResultException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable long id) {

        try {
            cadastroCidadeService.remover(id);
            return ResponseEntity.noContent().build();
        } catch(NoResultException e) {
            return ResponseEntity.notFound().build();
        } catch(DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

}
