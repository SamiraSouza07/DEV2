package com.example.apibancopicpay.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Conta {
    @Id
    private String numero_conta;
    private double saldo;
    private double limite_especial;
    @NotNull(message = "O cpf não pode ser nulo")
    @Size(min = 11, max = 11, message="O cpf deve ter 11 números")
    private String cliente_cpf;

    public Conta(String numero_conta, double saldo, double limite_especial, String cliente_cpf) {
        this.numero_conta = numero_conta;
        this.saldo = saldo;
        this.limite_especial = limite_especial;
        this.cliente_cpf = cliente_cpf;
    }

    public Conta() {
    }

    public String getNumero_conta() {
        return numero_conta;
    }

    public void setNumero_conta(String numero_conta) {
        this.numero_conta = numero_conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimite_especial() {
        return limite_especial;
    }

    public void setLimite_especial(double limite_especial) {
        this.limite_especial = limite_especial;
    }

    public String getCliente_cpf() {
        return cliente_cpf;
    }

    public void setCliente_cpf(String cliente_cpf) {
        this.cliente_cpf = cliente_cpf;
    }
}
