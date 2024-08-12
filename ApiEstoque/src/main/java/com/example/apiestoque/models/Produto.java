package com.example.apiestoque.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@SequenceGenerator(name = "produto_id_seq", sequenceName = "produto_id_seq", allocationSize=1)
public class Produto {
    @Id
    @GeneratedValue(generator="produto_id_seq", strategy= GenerationType.SEQUENCE)
    @Schema(description="Id do produto", example = "101")
    private long id;
    @NotNull(message = "O nome não pode ser nulo")
    @Size(min = 2, message = "O nome deve ter pelo menos 2 caracteres")
    @Schema(description="Nome do produto", example = "Batatas sorriso turma da mônica")
    private String nome;
    @Size(min = 2, message = "A descrição deve ter pelo menos 10 caracteres")
    @Schema(description = "Descrição detalhada do produto",example="Batatas congeladas de 200g")
    private String descricao;
    @NotNull(message = "O preço não pode ser nulo")
    @Min(value = 0, message = "O preço deve ser pelo menos 0")
    @Schema(description = "Preço do produto",example = "8.99")
    private double preco;
    @NotNull(message = "A quantidade não pode ser nula")
    @Min(value = 0, message = "A quantidade deve ser pelo menos 0")
    @Schema(description = "Quantidade disponível em estoque",example = "150")
    @Column(name="quantidadeestoque")
    private int quantidadeEstoque;

    public Produto() {
    }

    public Produto(int id, String nome, String descricao, double preco, int quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @Override
    public String toString() {
        return  "id: " + id +
                "\nNome: " + nome +
                "\nDescrição: " + descricao +
                "\nPreço: " + preco +
                "\nQuantidade em estoque=" + quantidadeEstoque;
    }
}
