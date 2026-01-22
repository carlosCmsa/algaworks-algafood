package com.algaworks.algafood_api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood_api.domain.model.Cozinha;
import java.util.List;


@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
    
    List<Cozinha> findByNomeContaining(String nome);

}
