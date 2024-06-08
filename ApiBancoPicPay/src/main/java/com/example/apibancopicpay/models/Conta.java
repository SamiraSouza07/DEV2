package com.example.apibancopicpay.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Conta {
    @Id
    @Column(name = "numero_conta")
    private String numeroConta;
    private double saldo;
    @Column(name = "limite_especial")
    private double limiteEspecial;
    @NotNull(message = "O cpf não pode ser nulo")
    @Size(min = 11, max = 11, message="O cpf deve ter 11 números")
    private String cliente_cpf;

    public Conta(String numeroConta, double saldo, double limiteEspecial, String cliente_cpf) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.limiteEspecial = limiteEspecial;
        this.cliente_cpf = cliente_cpf;
    }

    public Conta() {
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numero_conta) {
        this.numeroConta = numero_conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimiteEspecial() {
        return limiteEspecial;
    }

    public void setLimiteEspecial(double limite_especial) {
        this.limiteEspecial = limite_especial;
    }

    public String getCliente_cpf() {
        return cliente_cpf;
    }

    public void setCliente_cpf(String cliente_cpf) {
        this.cliente_cpf = cliente_cpf;
    }
}
