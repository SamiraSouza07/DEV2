package com.example.apiestoque.service;

import com.example.apiestoque.models.Produto;
import com.example.apiestoque.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> buscarTodosOsProdutos(){
        return produtoRepository.findAll();
    }

    public Produto salvarProduto(Produto produto){
        return produtoRepository.save(produto);
    }
    public Produto buscarProdutoPorId(Long id){
        return produtoRepository.findById(id).orElseThrow(()->
            new RuntimeException("Produto não encontrado"));
    }

    public Produto excluirProduto(Long id){
        Optional<Produto> prod = produtoRepository.findById(id);
        if(prod.isPresent()){
            produtoRepository.deleteById(id);
            return prod.get();
        }
        return null;
    }
    public List<Produto> buscarPorNome(String nome){
        return produtoRepository.findByNomeLikeIgnoreCase(nome);
    }
    public List<Produto> buscarPorPrecoMenorIgual(double preco){
        return produtoRepository.findByPrecoIsLessThanEqual(preco);
    }
    public List<Produto> buscarPorPrecoMaiorIgual(double preco){
        return produtoRepository.findByPrecoIsGreaterThanEqual(preco);
    }
}
