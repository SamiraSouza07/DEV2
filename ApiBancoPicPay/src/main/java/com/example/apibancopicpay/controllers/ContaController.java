package com.example.apibancopicpay.controllers;

import com.example.apibancopicpay.models.Cliente;
import com.example.apibancopicpay.models.Conta;
import com.example.apibancopicpay.service.ContaService;
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
@RequestMapping("/apiPicPay/conta")
public class ContaController {
        private final ContaService contaService;
        private final Validator validator;

        public ContaController(ContaService contaService, Validator validator) {
            this.contaService = contaService;
            this.validator = validator;
        }

        @GetMapping("/selecionar")
        public List<Conta> listarProdutos() {
            return contaService.listarContas();
        }

        @PostMapping("/inserir")
        public ResponseEntity<String> inserirConta(@RequestBody @Valid Conta conta,BindingResult resultado){
            conta.setNumero_conta(contaService.gerarNumeroConta());
            if(resultado.hasErrors()){
                return ResponseEntity.status(400).body(checarErros(resultado));
            }
            contaService.salvarConta(conta);
            Conta contaVerif = contaService.buscarContaPorId(conta.getNumero_conta());
            if(!(contaVerif == null)){
                return ResponseEntity.status(201).body("Conta criada com sucesso");
            }
            else{
                return ResponseEntity.status(500).body("Não foi possível criar a conta");
            }
        }
        @DeleteMapping("/excluir/{num_conta}")
        public ResponseEntity<String> excluirProduto(@Valid @PathVariable String num_conta){
            Conta conta = contaService.excluirConta(num_conta);
            if(conta != null){
                return ResponseEntity.ok("Conta excluida com sucesso");
            }else{
                return ResponseEntity.status(404).body("Este número de conta não existe");
            }
        }
        @PatchMapping("/atualizar/{id}")
        public ResponseEntity<String> atualizarConta(@PathVariable String num_conta,
                                                       @RequestBody Map<String, Object> updates){
            Conta contaExistente = contaService.buscarContaPorId(num_conta);
            if(contaExistente != null){
                Conta conta = contaExistente;

                if(updates.containsKey("saldo")){
                    try {
                        conta.setSaldo((double) updates.get("saldo"));
                    }catch(ClassCastException cne){
                        int saldoInt = (Integer) updates.get("saldo");
                        conta.setSaldo(Double.parseDouble(String.valueOf(saldoInt)));
                    }
                }
                if(updates.containsKey("limite_especial")){
                    try {
                        conta.setLimite_especial((double) updates.get("limite_especial"));
                    }catch(ClassCastException cne){
                        int limiteInt = (Integer) updates.get("limite_especial");
                        conta.setSaldo(Double.parseDouble(String.valueOf(limiteInt)));
                    }
                }
                //validando entradas
                DataBinder binder = new DataBinder(conta);
                binder.setValidator(validator);
                binder.validate();
                BindingResult resultado =binder.getBindingResult();
                if(resultado.hasErrors()){
                    return ResponseEntity.status(400).body(checarErros(resultado));
                }
                contaService.salvarConta(conta);
                return ResponseEntity.status(200).body("Conta alterada com sucesso");
            }
            return ResponseEntity.status(404).body("Conta não encontrada");
        }
        @PostMapping("/depositar/{numero_conta}")
        public ResponseEntity<?> depositar(@PathVariable String numero_conta,
                                           @RequestParam double valorDeposito ){
            boolean retornoDeposito;
            Conta contaExistente;
            try {
                contaExistente = contaService.buscarContaPorId(numero_conta);
            }catch (RuntimeException re){
                return ResponseEntity.status(404).body("Não foi possivel encontrar uma conta com o número: "+numero_conta);
            }
            if(contaExistente!=null){
                if(valorDeposito>0){
                    retornoDeposito= contaService.depositarDinheiro(numero_conta,valorDeposito);
                }else{
                    return ResponseEntity.status(400).body("Insira um valor positivo para depositar");
                }
                if(retornoDeposito){
                    contaService.salvarConta(contaExistente);
                    return ResponseEntity.status(200).body(contaExistente);
                }else{
                    return ResponseEntity.status(500).body("Não foi possível depositar dinheiro");
                }
            }else {
                return ResponseEntity.status(404).body("Não foi possivel encontrar uma conta com o número: "+numero_conta);
            }
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
