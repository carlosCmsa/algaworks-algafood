package com.algaworks.algafood_api.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;
import com.algaworks.algafood_api.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/api/v1/testes")
public class TesteController {
    
    @Autowired
    CozinhaRepository cozinhaRepository;

    @Autowired
    RestauranteRepository restauranteRepository;

    @GetMapping("/cozinhas")
    public ResponseEntity<List<Cozinha>> listarByName(@RequestParam String nome) {
        return ResponseEntity.ok(cozinhaRepository.findByNomeContaining(nome));
    }

    @GetMapping("/restaurantes")
    public ResponseEntity<List<Restaurante>> findByNomeAndTaxaFrete(@RequestParam Boolean ativo, 
                                                                    @RequestParam BigDecimal taxaFreteInicial,
                                                                    @RequestParam BigDecimal taxaFreteFinal) {
        return ResponseEntity.ok(restauranteRepository.findByAtivoAndTaxaFreteBetween(ativo, taxaFreteInicial, taxaFreteFinal));
    }

    @GetMapping("/restaurantes/nome")
    public ResponseEntity<Restaurante> findFirstByNomeContainingOrderByIdDesc(@RequestParam String nome) {
        return ResponseEntity.ok(restauranteRepository.findFirstByNomeContainingOrderByIdDesc(nome).get());
    }

    @GetMapping("/restaurantes/nome/exists")
    public ResponseEntity<Boolean> existsByNome(@RequestParam String nome) {
        return ResponseEntity.ok(restauranteRepository.existsByNome(nome));
    }

    @GetMapping("/restaurantes/nome/count")
    public ResponseEntity<Integer> countByNome(@RequestParam String nome) {
        return ResponseEntity.ok(restauranteRepository.countByNome(nome));
    }

    @GetMapping("/restaurantes/nome/frete")
    public ResponseEntity<Restaurante> consultarPorNomeETaxaFrete(@RequestParam String nome, @RequestParam BigDecimal taxaFrete) {
        return ResponseEntity.ok(restauranteRepository.consultarPorNomeETaxaFrete(nome, taxaFrete).get());
    }

    @GetMapping("/restaurantes/nome/cozinha")
    public ResponseEntity<Restaurante> consultarPorCozinhaENome(@RequestParam Long cozinhaId, @RequestParam String nome) {
        return ResponseEntity.ok(restauranteRepository.consultarPorCozinhaENome(cozinhaId, nome).get());
    }

}
