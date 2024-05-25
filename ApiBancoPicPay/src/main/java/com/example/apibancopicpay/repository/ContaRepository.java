package com.example.apibancopicpay.repository;

import com.example.apibancopicpay.models.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, String> {
}
