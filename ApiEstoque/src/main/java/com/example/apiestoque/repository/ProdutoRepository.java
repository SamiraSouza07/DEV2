package com.example.apiestoque.repository;
import com.example.apiestoque.models.Produto;
import jakarta.validation.constraints.DecimalMax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    @Modifying
    @Query ("DELETE FROM Produto e WHERE e.id = ?1")
    void deleteById(Long id);

    List<Produto> findByNomeLikeIgnoreCase(String nome);
    List<Produto> findByPrecoIsLessThanEqual(double preco);
    List<Produto> findByPrecoIsGreaterThanEqual(double preco);
}
