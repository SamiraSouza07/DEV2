package com.example.apiestoque.controllers;

import com.example.apiestoque.models.Produto;
import com.example.apiestoque.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;
    private final Validator validator;

    public ProdutoController(ProdutoService produtoService, Validator validator) {
        this.produtoService = produtoService;
        this.validator = validator;
    }

    @GetMapping("/selecionar")
    public List<Produto> listarProdutos(){
        return produtoService.buscarTodosOsProdutos();
    }

    @GetMapping("/selecionarPorNome")
    public ResponseEntity<?> listarProdutosPorNome(@RequestParam String nome){
        List<Produto> listaProdutos = produtoService.buscarPorNome(nome);
        if(!listaProdutos.isEmpty()){
            return ResponseEntity.status(201).body(listaProdutos);
        }else{
            return ResponseEntity.status(404).body("Não foi possível achar produtos com este nome");
        }
    }

    @GetMapping("/selecionarProPrecoMenorQue")
    public ResponseEntity<?> listarProdutosPorPrecoMenorQue(@RequestParam double preco){
        List<Produto> listaProdutos = produtoService.buscarPorPrecoMenorIgual(preco);
        if(!listaProdutos.isEmpty()){
            return ResponseEntity.status(201).body(listaProdutos);
        }else{
            return ResponseEntity.status(404).body("Não foi possível achar produtos com preço menor ou igual a "+preco);
        }
    }

    @PostMapping("/inserir")
    public ResponseEntity<String> inserirProduto(@RequestBody @Valid Produto produto, BindingResult resultado){
        if(resultado.hasErrors()){
            return ResponseEntity.status(400).body(checarErros(resultado));
        }
        produtoService.salvarProduto(produto);
        return ResponseEntity.status(201).body("Produto inserido com sucesso");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirProduto(@Valid @PathVariable Long id){
        Produto produto = produtoService.excluirProduto(id);
        if(produto != null){
            return ResponseEntity.ok("Produto excluido com sucesso");
        }else{
            return ResponseEntity.status(404).body("Este id não existe");
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarProduto(@PathVariable Long id,@RequestBody @Valid Produto produtoAtualizado, BindingResult resultado){
        Produto produtoExistente = produtoService.buscarProdutoPorId(id);
        if(produtoExistente != null) {
            Produto produto = produtoExistente;
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setDescricao(produtoAtualizado.getDescricao());
            produto.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
            if(resultado.hasErrors()){
                return ResponseEntity.status(400).body(checarErros(resultado));
            }
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
                try {
                    produto.setPreco((Double) updates.get("preco"));
                }catch(ClassCastException cne){
                    int precoInt = (Integer) updates.get("preco");
                    produto.setPreco(Double.parseDouble(String.valueOf(precoInt)));
                }
            }
            //validando entradas
            DataBinder binder = new DataBinder(produto);
            binder.setValidator(validator);
            binder.validate();
            BindingResult resultado =binder.getBindingResult();
            if(resultado.hasErrors()){
                return ResponseEntity.status(400).body(checarErros(resultado));
            }
            produtoService.salvarProduto(produto);
            return ResponseEntity.status(200).body("Produto alterado com sucesso");
        }
        return ResponseEntity.status(404).body("Produto não encontrado");
    }
    public String checarErros(BindingResult resultado){
        List<FieldError> errors = resultado.getFieldErrors();
        String erro="";
        for (FieldError error : errors ) {
            erro += "campo: "+error.getField()+"\nerro: "+error.getDefaultMessage()+"\n\n";
        }
        return erro;

    }
}
