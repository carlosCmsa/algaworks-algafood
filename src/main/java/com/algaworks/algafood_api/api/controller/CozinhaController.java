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

import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.service.CadastroCozinhaService;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/api/v1/cozinhas")
public class CozinhaController {
    
    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @GetMapping
    public ResponseEntity<List<Cozinha>> listar() {
        return ResponseEntity.ok(cadastroCozinhaService.listar());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable long id) {
        
        try {
            return ResponseEntity.ok(cadastroCozinhaService.buscar(id));
        } catch(NoResultException e) {
            return ResponseEntity.notFound().build();
        } 
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha body, HttpServletRequest http) {
        Cozinha cozinha = cadastroCozinhaService.adicionar(body);
        URI uri = URI.create(String.format("%s/%s", http.getRequestURI(), body.getId()));

        return ResponseEntity
            .created(uri)
            .body(cozinha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable long id, @RequestBody Cozinha body) {
        return ResponseEntity.ok(cadastroCozinhaService.atualizar(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable long id) {
        
        try {
            cadastroCozinhaService.remover(id);
        } catch(DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch(NoResultException e) {
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();

    }

}
