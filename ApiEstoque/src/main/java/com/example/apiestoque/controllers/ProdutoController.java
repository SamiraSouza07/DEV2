package com.example.apiestoque.controllers;

import com.example.apiestoque.models.Produto;
import com.example.apiestoque.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/selecionar")
    public List<Produto> listarProdutos(){
        return produtoService.buscarTodosOsProdutos();
    }

    @PostMapping("/inserir")
    public ResponseEntity<String> inserirProduto(@RequestBody Produto produto){
        produtoService.salvarProduto(produto);
        return ResponseEntity.status(201).body("Produto inserido com sucesso");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirProduto(@PathVariable Long id){
        Produto produto = produtoService.excluirProduto(id);
        if(produto != null){
            return ResponseEntity.ok("Produto excluido com sucesso");
        }else{
            return ResponseEntity.status(404).body("Este id não existe");
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarProduto(@PathVariable Long id,@RequestBody Produto produtoAtualizado){
        Produto produtoExistente = produtoService.buscarProdutoPorId(id);
        if(produtoExistente != null) {
            Produto produto = produtoExistente;
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setDescricao(produtoAtualizado.getDescricao());
            produto.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
            produtoService.salvarProduto(produto);
            return ResponseEntity.status(200).body("Produto atualizado com sucesso");
        }else{
            return ResponseEntity.status(404).body("Este id não existe");
        }
    }

    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<String> atualizarProdutoParcial(@PathVariable Long id,
                                                          @RequestBody Map<String, Object> updates){
        Produto produtoExistente = produtoService.buscarProdutoPorId(id);
        if(produtoExistente != null){
            Produto produto = produtoExistente;
            if(updates.containsKey("nome")){
                produto.setNome((String) updates.get("nome"));
            }
            if(updates.containsKey("descricao")){
                produto.setDescricao((String) updates.get("descricao"));
            }
            if(updates.containsKey("quantidadeEstoque")){
                produto.setQuantidadeEstoque((Integer) updates.get("quantidadeEstoque"));
            }
            if(updates.containsKey("preco")){
                produto.setPreco((Double) updates.get("preco"));
            }
            produtoService.salvarProduto(produto);
            return ResponseEntity.status(200).body("Produto alterado com sucesso");
        }
        return ResponseEntity.status(404).body("Produto não encontrado");
    }
}
