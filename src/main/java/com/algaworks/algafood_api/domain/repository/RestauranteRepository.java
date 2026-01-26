package com.algaworks.algafood_api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood_api.domain.model.Restaurante;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, CustomizedRestauranteRepository {

    List<Restaurante> findByAtivoAndTaxaFreteBetween(boolean ativo, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

    Optional<Restaurante> findFirstByNomeContainingOrderByIdDesc(String nome);

    boolean existsByNome(String nome);

    int countByNome(String nome);

    @Query(value = "from Restaurante where nome like %:nome% and taxaFrete = :taxa")
    Optional<Restaurante> consultarPorNomeETaxaFrete(@Param(value = "nome") String nomeRestaurante, @Param(value = "taxa") BigDecimal taxaFrete);

    Optional<Restaurante> consultarPorCozinhaENome(long cozinhaId, String nome);

}
