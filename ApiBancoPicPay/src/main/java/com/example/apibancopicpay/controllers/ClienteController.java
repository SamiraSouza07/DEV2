package com.example.apibancopicpay.controllers;

import com.example.apibancopicpay.models.Cliente;
import com.example.apibancopicpay.models.Conta;
import com.example.apibancopicpay.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiPicPay/cliente")
public class ClienteController {
        private final ClienteService clienteService;
        private final Validator validator;

        public ClienteController(ClienteService clienteService, Validator validator) {
            this.clienteService = clienteService;
            this.validator = validator;
        }

        @GetMapping("/selecionar")
        public List<Cliente> listarClientes() {
            return clienteService.listarClientes();
        }

        @GetMapping("/selecionarPorNome")
        public ResponseEntity<?> listarClientesPorNome(@RequestParam String nome){
            List<Cliente> listarClientes = clienteService.buscarClientesPorNome(nome);
            if(!listarClientes.isEmpty()){
                return ResponseEntity.status(201).body(listarClientes);
            }else{
                return ResponseEntity.status(404).body("Não foi possível achar clientes com este nome");
            }
        }
        @GetMapping("/selecionarPorCpf")
        public ResponseEntity<?> listarClientesPorCpf(@RequestParam String cpf){
            List<Cliente> listarClientes = clienteService.buscarClientesPorCpf(cpf);
            if(!listarClientes.isEmpty()){
                return ResponseEntity.status(201).body(listarClientes);
            }else{
                return ResponseEntity.status(404).body("Não foi possível achar um cliente com este cpf");
            }
        }
        @GetMapping("/selecionarPorTelefone")
        public ResponseEntity<?> listarClientesPorTelefone(@RequestParam String telefone){
            List<Cliente> listarClientes = clienteService.buscarClientesPorTelefone(telefone);
            if(!listarClientes.isEmpty()){
                return ResponseEntity.status(201).body(listarClientes);
            }else{
                return ResponseEntity.status(404).body("Não foi possível achar um cliente com este telefone");
            }
        }
        @GetMapping("/selecionarPorEmail")
        public ResponseEntity<?> listarClientesPorEmail(@RequestParam String email){
            List<Cliente> listarClientes = clienteService.buscarClientesPorEmail(email);
            if(!listarClientes.isEmpty()){
                return ResponseEntity.status(201).body(listarClientes);
            }else{
                return ResponseEntity.status(404).body("Não foi possível achar um cliente com este email");
            }
        }
        @GetMapping("/selecionarPorNomeEEmail")
        public ResponseEntity<?> listarClientesPorNomeEEmail(@RequestParam String nome, String email){
            List<Cliente> listarClientes = clienteService.buscarClientesPorNomeEEmail(nome,email);
            if(!listarClientes.isEmpty()){
                return ResponseEntity.status(201).body(listarClientes);
            }else{
                return ResponseEntity.status(404).body("Não foi possível achar clientes com este nome e este email");
            }
        }
        @GetMapping("/selecionarPorNomeETelefone")
        public ResponseEntity<?> listarClientesPorNomeETelefone(@RequestParam String nome,String telefone){
            List<Cliente> listarClientes = clienteService.buscarClientesPorNomeETelefone(nome,telefone);
            if(!listarClientes.isEmpty()){
                return ResponseEntity.status(201).body(listarClientes);
            }else{
                return ResponseEntity.status(404).body("Não foi possível achar clientes com este nome e este telefone");
            }
        }
        @GetMapping("/selecionarPorNomeECpf")
        public ResponseEntity<?> listarClientesPorNomeECpf(@RequestParam String nome,String cpf){
            List<Cliente> listarClientes = clienteService.buscarClientesPorNomeECpf(nome,cpf);
            if(!listarClientes.isEmpty()){
                return ResponseEntity.status(201).body(listarClientes);
            }else{
                return ResponseEntity.status(404).body("Não foi possível achar clientes com este nome e este cpf");
            }
        }

        @PostMapping("/inserir")
        public ResponseEntity<String> inserirCliente(@RequestBody @Valid Cliente cliente,BindingResult resultado){
            if(resultado.hasErrors()){
                return ResponseEntity.status(400).body(checarErros(resultado));
            }
            clienteService.salvarCliente(cliente);
            Cliente clienteVerif = clienteService.buscarClientePorId(cliente.getCpf());
            if(!(clienteVerif == null)){
                return ResponseEntity.status(201).body("Cliente cadastrado com sucesso");
            }else{
                return ResponseEntity.status(500).body("Não foi possível cadastrar o cliente");
            }
        }
        @DeleteMapping("/excluir/{cpf}")
        public ResponseEntity<String> excluirCliente(@Valid @PathVariable String cpf){
            Cliente cliente = clienteService.excluirCliente(cpf);
            if(cliente != null){
                return ResponseEntity.ok("Cliente excluido com sucesso");
            }else{
                return ResponseEntity.status(404).body("Este cpf não existe");
            }
        }
        @PatchMapping("/atualizar/{cpf}")
        public ResponseEntity<String> atualizarCliente(@PathVariable String cpf,
                                                              @RequestBody Map<String, Object> updates){
            Cliente clienteExistente = clienteService.buscarClientePorId(cpf);
            if(clienteExistente != null){
                Cliente cliente = clienteExistente;
                if(updates.containsKey("nome")){
                    cliente.setNome((String) updates.get("nome"));
                }
                if(updates.containsKey("email")){
                    cliente.setEmail((String) updates.get("email"));
                }
                if(updates.containsKey("telefone")){
                    cliente.setTelefone((String) updates.get("telefone"));
                }
                //validando entradas
                DataBinder binder = new DataBinder(cliente);
                binder.setValidator(validator);
                binder.validate();
                BindingResult resultado =binder.getBindingResult();
                if(resultado.hasErrors()){
                    return ResponseEntity.status(400).body(checarErros(resultado));
                }
                clienteService.salvarCliente(cliente);
                return ResponseEntity.status(200).body("Cliente alterado com sucesso");
            }
            return ResponseEntity.status(404).body("Cliente não encontrado");
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
