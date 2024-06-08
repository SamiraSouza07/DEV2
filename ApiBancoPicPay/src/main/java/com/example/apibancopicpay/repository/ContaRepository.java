package com.example.apibancopicpay.repository;

import com.example.apibancopicpay.models.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, String> {
    @Modifying
    @Query("DELETE FROM Conta e WHERE e.numeroConta = ?1")
    void deleteById(String numero_conta);

    List<Conta> findBySaldoBetweenOrderByNumeroConta(double valor1, double valor2);
    List<Conta> findByLimiteEspecialBetweenOrderByNumeroConta(double valor1, double valor);
    List<Conta> findBySaldoLessThanEqualOrderByNumeroConta(double saldo);


}
