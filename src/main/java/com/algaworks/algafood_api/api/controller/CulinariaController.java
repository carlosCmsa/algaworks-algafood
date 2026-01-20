package com.algaworks.algafood_api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;

@RestController
@RequestMapping(path = "/api/v1/culinarias")
public class CulinariaController {
    
    @Autowired
    CozinhaController cozinhaController;

    @Autowired
    CozinhaRepository cozinhaRepository;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/api/v1/cozinhas");

        return ResponseEntity
            .status(HttpStatus.FOUND)
            .headers(headers)
            .build();
    }

}
