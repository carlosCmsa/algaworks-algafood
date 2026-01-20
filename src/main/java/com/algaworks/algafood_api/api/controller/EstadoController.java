package com.algaworks.algafood_api.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood_api.domain.model.Estado;
import com.algaworks.algafood_api.domain.repository.EstadoRepository;

@RestController
@RequestMapping(path = "/api/v1/estados")
public class EstadoController {
    
    @Autowired
    EstadoRepository estadoRepository;
    
    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.listar();
    }

}
