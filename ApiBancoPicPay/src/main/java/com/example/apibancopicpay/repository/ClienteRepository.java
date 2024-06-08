package com.example.apibancopicpay.repository;

import com.example.apibancopicpay.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    @Modifying
    @Query("DELETE FROM Cliente e WHERE e.cpf = ?1")
    void deleteById(String cpf);
    List<Cliente> findByNomeLikeIgnoreCaseOrderByNome(String nome);
    List<Cliente> findByCpfLikeOrderByNome(String cpf);
    List<Cliente> findByTelefoneLikeOrderByNome(String telefone);
    List<Cliente> findByEmailLikeIgnoreCaseOrderByNome(String email);
    List<Cliente> findByNomeLikeIgnoreCaseAndEmailLikeIgnoreCaseOrderByNome(String nome, String email);

    List<Cliente> findByNomeLikeIgnoreCaseAndTelefoneLikeOrderByNome(String nome, String telefone);
    List<Cliente> findByNomeLikeIgnoreCaseAndCpfLikeOrderByNome(String nome, String cpf);
}
