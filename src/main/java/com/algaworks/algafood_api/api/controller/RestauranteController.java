package com.algaworks.algafood_api.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.service.CadastroRestauranteService;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/restaurantes")
public class RestauranteController {
    
    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar() {
        return ResponseEntity.ok(cadastroRestauranteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable long id) {
        
        try {
            return ResponseEntity.ok(cadastroRestauranteService.buscar(id));
        } catch(NoResultException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Restaurante> adicionar(@RequestBody Restaurante body, HttpServletRequest http) {

        try {
            Restaurante restaurante = cadastroRestauranteService.adicionar(body);

            URI uri = URI.create(String.format("%s/%s", http.getRequestURI(), restaurante.getId()));

            return ResponseEntity.created(uri).build();
        } catch(NoResultException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable long id, @RequestBody Restaurante body) {
        
        try {
            return ResponseEntity.ok(cadastroRestauranteService.atualizar(id, body));
        } catch(NoResultException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable long id, @RequestBody Map<String, Object> body) {

        try {
            return ResponseEntity.ok(cadastroRestauranteService.atualizar(id, body));
        } catch(NoResultException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable long id) {

        try {
            cadastroRestauranteService.remover(id);
            return ResponseEntity.noContent().build();
        } catch(NoResultException e) {
            return ResponseEntity.notFound().build();
        } catch(DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

}
