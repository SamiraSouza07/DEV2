package com.example.apibancopicpay.controllers;

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

        @GetMapping("/selecionarContasPorSaldoEntre")
        public ResponseEntity<?> listarContasPorSaldoEntre(@RequestParam double valor1, double valor2){
            List<Conta> listaContas = contaService.buscarContasPorSaldoEntre(valor1,valor2);
            if(!listaContas.isEmpty()){
                return ResponseEntity.status(201).body(listaContas);
            }else{
                return ResponseEntity.status(404).body("Não foi possível achar contas com saldo neste intervalo");
            }
            }
        @GetMapping("/selecionarContasPorSaldoMenor")
        public ResponseEntity<?> listarContasPorSaldoMenor(@RequestParam double saldo){
            List<Conta> listaContas = contaService.buscarContasPorSaldoMenorQue(saldo);
            if(!listaContas.isEmpty()){
                return ResponseEntity.status(201).body(listaContas);
            }else{
                return ResponseEntity.status(404).body("Não foi possível achar contas com saldo neste intervalo");
            }
        }
        @GetMapping("/selecionarContasPorLimite")
        public ResponseEntity<?> listarContasPorLimiteEntre(@RequestParam double valor1, double valor2){
            List<Conta> listaContas = contaService.buscarContasPorLimiteEntre(valor1,valor2);
            if(!listaContas.isEmpty()){
                return ResponseEntity.status(201).body(listaContas);
            }else{
                return ResponseEntity.status(404).body("Não foi possível achar contas com limite especial neste intervalo");
            }
        }

        @PostMapping("/inserir")
        public ResponseEntity<String> inserirConta(@RequestBody @Valid Conta conta,BindingResult resultado){
            conta.setNumeroConta(contaService.gerarNumeroConta());
            if(resultado.hasErrors()){
                return ResponseEntity.status(400).body(checarErros(resultado));
            }
            contaService.salvarConta(conta);
            Conta contaVerif = contaService.buscarContaPorId(conta.getNumeroConta());
            if(!(contaVerif == null)){
                return ResponseEntity.status(201).body("Conta criada com sucesso");
            }
            else{
                return ResponseEntity.status(500).body("Não foi possível criar a conta");
            }
        }
        @DeleteMapping("/excluir/{numeroConta}")
        public ResponseEntity<String> excluirProduto(@Valid @PathVariable String numeroConta){
            Conta conta = contaService.excluirConta(numeroConta);
            if(conta != null){
                return ResponseEntity.ok("Conta excluida com sucesso");
            }else{
                return ResponseEntity.status(404).body("Este número de conta não existe");
            }
        }
        @PatchMapping("/atualizar/{numeroConta}")
        public ResponseEntity<String> atualizarConta(@PathVariable String numeroConta,
                                                       @RequestBody Map<String, Object> updates){
            Conta contaExistente = contaService.buscarContaPorId(numeroConta);
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
                if(updates.containsKey("limiteEspecial")){
                    try {
                        conta.setLimiteEspecial((double) updates.get("limiteEspecial"));
                    }catch(ClassCastException cne){
                        int limiteInt = (Integer) updates.get("limiteEspecial");
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
        @PostMapping("/depositar/{numeroConta}")
        public ResponseEntity<?> depositar(@PathVariable String numero_conta,
                                           @RequestParam double valorDeposito ){
            int retornoDeposito;
            if(valorDeposito>0){
                retornoDeposito= contaService.depositarDinheiro(numero_conta,valorDeposito);
            }else{
                return ResponseEntity.status(400).body("Insira um valor positivo para depositar");
            }
            if(retornoDeposito==1){
                return ResponseEntity.status(200).body("Deposito executado com sucesso");
            }else if(retornoDeposito==0) {
                return ResponseEntity.status(404).body("Não foi possível encontrar uma conta com o número: "+numero_conta);
            }else{
                return ResponseEntity.status(500).body("Não foi possível exucutar o depósito");
            }

        }
        @PostMapping("/sacar/{numeroConta}")
        public ResponseEntity<?> sacar(@PathVariable String numeroConta,
                                           @RequestParam double valorSaque ){
            int retornoSaque;
            if(valorSaque>0){
                retornoSaque= contaService.sacarDinheiro(numeroConta,valorSaque);
            }else{
                return ResponseEntity.status(400).body("Insira um valor positivo para sacar");
            }
            if(retornoSaque==1){
                return ResponseEntity.status(200).body("Saque executado com sucesso");
            }else if(retornoSaque==0){
                return ResponseEntity.status(400).body("Só é permitido sacar até 1000 reais nos finais de semana");
            }else if(retornoSaque==2){
                return ResponseEntity.status(400).body("O valor do saque estoura o limite especial");
            }else if(retornoSaque==3){
                return ResponseEntity.status(404).body("Não foi possível encontrar uma conta com o número: "+numeroConta);
            }else{
                return ResponseEntity.status(500).body("Não foi possível executar o saque");
            }
        }

        @PostMapping("/transferir")
        public ResponseEntity<?> transferir(@RequestParam String numero_conta_origem, String numero_conta_destino, String tipoTransferencia,double valorTransferencia ){
            int retornoTransferencia;
            if(valorTransferencia>0){
                retornoTransferencia= contaService.transferir(numero_conta_origem,numero_conta_destino,tipoTransferencia, valorTransferencia);
            }else{
                return ResponseEntity.status(400).body("Insira um valor positivo para transferir");
            }
            if(retornoTransferencia==1){
                return ResponseEntity.status(200).body("Transferencia executada com sucesso");
            }else if(retornoTransferencia==0){
                return ResponseEntity.status(400).body("Só é permitido fazer transferencias de tipo TED em dia de semana das 8h às 17h");
            }else if(retornoTransferencia==2){
                return ResponseEntity.status(404).body("Não foi possível encontrar a conta origem com o número: "+numero_conta_origem);
            }else if(retornoTransferencia==3){
                return ResponseEntity.status(404).body("Não foi possível encontrar a conta destino com o número: "+numero_conta_destino);
            }else if(retornoTransferencia==4){
                return ResponseEntity.status(400).body("O valor da transferencia estoura o limite especial");
            }else{
                return ResponseEntity.status(500).body("Não foi possível executar a transferencia");
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


