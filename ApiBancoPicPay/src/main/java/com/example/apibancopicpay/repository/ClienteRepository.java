package com.example.apibancopicpay.repository;

import com.example.apibancopicpay.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    List<Cliente> findByNomeLikeIgnoreCaseOrderByNome(String nome);
    List<Cliente> findByCpfLikeOrderByNome(String cpf);
    List<Cliente> findByTelefoneLikeOrderByNome(String telefone);
    List<Cliente> findByEmailLikeIgnoreCaseOrderByNome(String email);
    List<Cliente> findByNomeLikeIgnoreCaseAndEmailLikeIgnoreCaseOrderByNome(String nome, String email);

    List<Cliente> findByNomeLikeIgnoreCaseAndTelefoneLikeOrderByNome(String nome, String telefone);
    List<Cliente> findByNomeLikeIgnoreCaseAndCpfLikeOrderByNome(String nome, String cpf);
}
