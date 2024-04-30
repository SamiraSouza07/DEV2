package com.example.apiestoque.models;

import jakarta.persistence.*;

@Entity
@SequenceGenerator(name = "produto_id_seq", sequenceName = "produto_id_seq", allocationSize=1)
public class Produto {
    @Id
    @GeneratedValue(generator="produto_id_seq", strategy= GenerationType.SEQUENCE)
    private long id;
    private String nome;
    private String descricao;
    private double preco;
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
