package com.example.apiestoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }
    @GetMapping("/selecionar")
    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    @PostMapping("/inserir")
    public ResponseEntity<String> inserirProduto(@RequestBody Produto produto){
        produtoRepository.save(produto);
        return ResponseEntity.status(201).body("Produto inserido com sucesso");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirProduto(@PathVariable Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent()){
            produtoRepository.deleteById(id);
            return ResponseEntity.ok("Produto excluido com sucesso");
        }else{
            return ResponseEntity.status(404).body("Este id não existe");
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarProduto(@PathVariable Long id,@RequestBody Produto produtoAtualizado){
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if(produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setDescricao(produtoAtualizado.getDescricao());
            produto.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
            produtoRepository.save(produto);
            return ResponseEntity.status(200).body("Produto atualizado com sucesso");
        }else{
            return ResponseEntity.status(404).body("Este id não existe");
        }
    }
}
