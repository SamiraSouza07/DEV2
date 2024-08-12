package com.example.apiestoque.controllers;

import com.example.apiestoque.models.Produto;
import com.example.apiestoque.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Api produto")
public class ProdutoController {
    private final ProdutoService produtoService;
    private final Validator validator;

    public ProdutoController(ProdutoService produtoService, Validator validator) {
        this.produtoService = produtoService;
        this.validator = validator;
    }
    @GetMapping("/selecionar")
    @Operation(summary = "Lista todos os produtos",description = "Retorna uma lista com todos os produtos cadastrados")
    @ApiResponses( value={
            @ApiResponse( responseCode = "200",description = "Lista todos os produtos cadastrados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "500",description = "Erro interno no servidor",
                    content = @Content)
    })
    public ResponseEntity<List<Produto>>listarProdutos(){
        List<Produto> listaProdutos = produtoService.buscarTodosOsProdutos();
        return ResponseEntity.status(200).body(listaProdutos);
    }

    @GetMapping("/selecionarPorNome")
    @Operation(summary="Seleciona produtos por nome",description = "Retorna uma lista de produtos com o nome informado")
    @ApiResponses(value={
    @ApiResponse(responseCode = "200",description = "Lista de produtos com o nome pesquisado", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Produto.class))),
    @ApiResponse(responseCode = "404",description = "Nenhum produto encontrado",content = @Content)})
    public ResponseEntity<?> listarProdutosPorNome(@Parameter(description = "Nome do produto que deseja pesquisar") @RequestParam String nome){
        List<Produto> listaProdutos = produtoService.buscarPorNome(nome);
        if(!listaProdutos.isEmpty()){
            return ResponseEntity.status(200).body(listaProdutos);
        }else{
            return ResponseEntity.status(404).body("Não foi possível achar produtos com este nome");
        }
    }

    @GetMapping("/selecionarProPrecoMenorQue")
    @Operation(summary="Seleciona produtos com preço menor ou igual que o valor indicado",description = "Retorna uma lista de produtos que tenham o preço menor ou igual ao que o valor informado")
    @ApiResponses(value={
    @ApiResponse(responseCode = "200",description = "Lista de produtos com o preço menor ou igual ao valor informado", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Produto.class))),
    @ApiResponse(responseCode = "404",description = "Nenhum produto encontrado",content = @Content)})
    public ResponseEntity<?> listarProdutosPorPrecoMenorQue(@Parameter(description = "Um valor positivo que pode ser flutuante, que vai indicar o preço máximo dos produtos que serão retornados") @RequestParam double preco){
        List<Produto> listaProdutos = produtoService.buscarPorPrecoMenorIgual(preco);
        if(!listaProdutos.isEmpty()){
            return ResponseEntity.status(200).body(listaProdutos);
        }else{
            return ResponseEntity.status(404).body("Não foi possível achar produtos com preço menor ou igual a "+preco);
        }
    }
    @GetMapping("/selecionarProPrecoMaiorQue")
    @Operation(summary="Seleciona produtos com preço maior ou igual que o valor indicado",description = "Retorna uma lista de produtos que tenham o preço maior ou igual ao que o valor informado")
    @ApiResponses(value={
    @ApiResponse(responseCode = "200",description = "Lista de produtos com o preço maior ou igual ao valor informado", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Produto.class))),
    @ApiResponse(responseCode = "404",description = "Nenhum produto encontrado",content = @Content)})
    public ResponseEntity<?> listarProdutosPorPrecoMaiorQue(@Parameter(description = "Um valor positivo que pode ser flutuante, que vai indicar o preço mínimo dos produtos que serão retornados") @RequestParam double preco){
        List<Produto> listaProdutos = produtoService.buscarPorPrecoMaiorIgual(preco);
        if(!listaProdutos.isEmpty()){
            return ResponseEntity.status(200).body(listaProdutos);
        }else{
            return ResponseEntity.status(404).body("Não foi possível achar produtos com preço maior ou igual a "+preco);
        }
    }

    @PostMapping("/inserir")
    @Operation(summary="Inserir produto",description = "Insere um produto")
    @ApiResponses(value={@ApiResponse(responseCode = "201",description = "Produto inserido com sucesso", content = @Content),
    @ApiResponse(responseCode = "400",description = "Erro ao inserir produto, parâmetro inválido",content = @Content)})
    public ResponseEntity<String> inserirProduto(@RequestBody @Valid Produto produto, BindingResult resultado){
        if(resultado.hasErrors()){
            return ResponseEntity.status(400).body(checarErros(resultado));
        }
        produtoService.salvarProduto(produto);
        return ResponseEntity.status(201).body("Produto inserido com sucesso");
    }

    @DeleteMapping("/excluir/{id}")
    @Operation(summary="Excluir produto",description = "Exclui um produto")
    @ApiResponses(value={@ApiResponse(responseCode = "200",description = "Produto excluido com sucesso", content = @Content),
            @ApiResponse(responseCode = "404",description = "Erro ao excluir produto, id não encontrado",content = @Content)})
    public ResponseEntity<String> excluirProduto(@Parameter(description = "Id do produto") @Valid @PathVariable Long id){
        Produto produto = produtoService.excluirProduto(id);
        if(produto != null){
            return ResponseEntity.status(200).body("Produto excluido com sucesso");
        }else{
            return ResponseEntity.status(404).body("Este id não existe");
        }
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary="Atualizar produto",description = "Atualiza um produto")
    @ApiResponses(value={@ApiResponse(responseCode = "200",description = "Produto atualizado com sucesso", content = @Content),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir produto",content = @Content),
    @ApiResponse(responseCode = "404",description = "Id não encontrado",content = @Content)})
    public ResponseEntity<String> atualizarProduto(@Parameter(description = "Id do produto que deseja atualizar") @PathVariable Long id,
                                                   @RequestBody @Valid Produto produtoAtualizado, BindingResult resultado){
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
    @Operation(summary="Atualizar produto parcialmente",description = "Atualiza um produto parcialmente (uma ou mais colunas)")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "Produto atualizado com sucesso", content = @Content),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir produto",content = @Content),
            @ApiResponse(responseCode = "404",description = "Id não encontrado",content = @Content)})
    public ResponseEntity<String> atualizarProdutoParcial(@Parameter(description = "Id do produto que deseja atualizar") @PathVariable Long id,
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
