package com.example.apiestoque.repository;
import com.example.apiestoque.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    @Modifying
    @Query ("DELETE FROM Produto e WHERE e.id = ?1")
    void deleteById(Long id);
}
