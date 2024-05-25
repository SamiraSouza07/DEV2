package com.example.apibancopicpay.service;


import com.example.apibancopicpay.models.Conta;
import com.example.apibancopicpay.repository.ContaRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ContaService {
    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public List<Conta> listarContas(){
        return contaRepository.findAll();
    }

    public void salvarConta(Conta conta){
        contaRepository.save(conta);
    }
    public Conta buscarContaPorId(String numero_conta){
        return contaRepository.findById(numero_conta).orElseThrow(()->
                new RuntimeException("Conta não encontrada"));
    }
    public String gerarNumeroConta(){
        Random random = new Random();
        boolean verifNumeroExiste = true;
        String numero_conta = "";
        while(verifNumeroExiste) {
            List<Conta> contas = listarContas();
            int primeiroDig = random.nextInt(0, 9);
            int segundoDig = random.nextInt(0, 9);
            int terceiroDig = random.nextInt(0, 9);
            int quartoDig = random.nextInt(0, 9);
            int digVerificador = (primeiroDig + segundoDig + terceiroDig + quartoDig) % 10;
            numero_conta = String.valueOf(primeiroDig);
            numero_conta += segundoDig;
            numero_conta += terceiroDig;
            numero_conta += quartoDig;
            numero_conta += digVerificador;
            for (Conta cont : contas) {
                if (cont.getNumero_conta() == numero_conta) {
                    verifNumeroExiste = true;
                }else{
                    verifNumeroExiste=false;
                }
            }
        }
        return numero_conta;
    }
    public Conta excluirConta(String numero_conta){
        Conta conta = buscarContaPorId(numero_conta);
        if(conta !=null){
            contaRepository.deleteById(numero_conta);
            return conta;
        }
        return null;
    }

    public boolean depositarDinheiro(String numero_conta, double valorDeposito){
        boolean depositoExecutado = false;
        Conta conta = buscarContaPorId(numero_conta);
        if(conta !=null){
            conta.setSaldo(conta.getSaldo()+valorDeposito);
            depositoExecutado = true;
        }
        return depositoExecutado;
    }
    public boolean sacarDinheiro(String numero_conta, double valorSacar){
        boolean sacarExecutado = false;
        Conta conta = buscarContaPorId(numero_conta);
        if(conta!=null){
            conta.setSaldo(conta.getSaldo()-valorSacar);
            sacarExecutado = true;
        }
        return sacarExecutado;
    }
}