package com.Treino.Treino.infraestructure.repository;

import com.Treino.Treino.infraestructure.entitys.Produtos;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutosRepository extends JpaRepository<Produtos, Integer> {

    Optional<Produtos> findByNome(String nome);

    @Transactional
    void deleteByNome(String nome);
}
