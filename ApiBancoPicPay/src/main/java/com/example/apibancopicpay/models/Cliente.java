package com.example.apibancopicpay.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente {
    @Id
    @NotNull(message = "O cpf não pode ser nulo")
    @Size(min = 11, max = 11, message="O cpf deve ter 11 números")
    private String cpf;
    @Size(min=3, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;
    @Email(message = "Digite um email valido")
    private String email;
    @Size(min=9, message = "O telefone deve ter pelo menos 9 números")
    private String telefone;

    public Cliente(String cpf, String nome, String email, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Cliente() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
