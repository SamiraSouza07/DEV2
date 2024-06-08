package com.example.apibancopicpay.service;


import com.example.apibancopicpay.models.Conta;
import com.example.apibancopicpay.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
                if (cont.getNumeroConta() == numero_conta) {
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

    public int depositarDinheiro(String numero_conta, double valorDeposito){
        //-1 - erro ao fazer o depósito
        //0 - conta não existe
        //1 - deposito executado com sucesso
        int depositoExecutado = -1;
        Conta conta;
        try {
            conta = buscarContaPorId(numero_conta);
        }catch(RuntimeException re){
            return 0;
        }
        if(conta!=null){
            conta.setSaldo(conta.getSaldo()+valorDeposito);
            salvarConta(conta);
            return 1;
        }
        return depositoExecutado;
    }
    public int sacarDinheiro(String numero_conta, double valorSacar){
        //-1 - erro ao fazer o saque
        //0 - valor do saque maior que 1000
        //1 - saque executado com sucesso
        //2 - valor do saque estoura o limite especial
        //3 - conta não encontrada
        int sacarExecutado = -1;
        Date data = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        int diaDaSemana = calendar.get(Calendar.DAY_OF_WEEK);
        Conta conta;
        try {
            conta = buscarContaPorId(numero_conta);
        }catch(RuntimeException re){
            return 3;
        }
        if(conta != null) {
            double saldoNovo = conta.getSaldo() - valorSacar;
            if (diaDaSemana == 1 || diaDaSemana == 7) {
                if (valorSacar > 1000) {
                    return 0;
                }
            }
            if (saldoNovo + conta.getLimiteEspecial() < 0) {
                return 2;
            } else {
                conta.setSaldo(conta.getSaldo() - valorSacar);
                salvarConta(conta);
                return 1;
            }
        }

        return sacarExecutado;
    }
    public int transferir(String contaOrigem, String contaDestino, String tipoTransacao, double valorTransferencia){
        //-1 - erro ao executar a transferencia
        //0 - dia da semana ou horário não há como fazer transferencia TED (dias de semana da 8h até as 17h)
        //1 - transferencia executada
        //2 - conta origem não encontrada
        //3 - conta destino não encontrada
        //4 - saldo da transferencia estoura o limite especial
        int transferenciaExecutada = -1;
        Conta conta1;
        Conta conta2;
        if(tipoTransacao.equalsIgnoreCase("TED")){
            Date data = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);
            int diaDaSemana = calendar.get(Calendar.DAY_OF_WEEK);
            if(diaDaSemana!=1||diaDaSemana!=7){
                int hora = calendar.get(Calendar.HOUR_OF_DAY);
                if(hora<8 && hora>17){
                    return 0;
                }
            }else{
                return 0;
            }

        }
        try {
            conta1 = buscarContaPorId(contaOrigem);
        }catch(RuntimeException re){
            return 2;
        }
        try {
            conta2 = buscarContaPorId(contaDestino);
        }catch(RuntimeException re){
            return 3;
        }
        double saldoNovo = conta1.getSaldo() - valorTransferencia;
        if (saldoNovo + conta1.getLimiteEspecial() < 0) {
            return 4;
        }
        if(conta1!= null && conta2!=null) {
            conta1.setSaldo(conta1.getSaldo() - valorTransferencia);
            conta2.setSaldo(conta2.getSaldo() + valorTransferencia);
            salvarConta(conta1);
            salvarConta(conta2);
            return 1;
        }

        return transferenciaExecutada;
    }
    public List<Conta> buscarContasPorSaldoEntre(double valor1, double valor2){
        return contaRepository.findBySaldoBetweenOrderByNumeroConta(valor1,valor2);
    }
    public List<Conta> buscarContasPorLimiteEntre(double valor1, double valor2){
        return contaRepository.findByLimiteEspecialBetweenOrderByNumeroConta(valor1,valor2);
    }
    public List<Conta> buscarContasPorSaldoMenorQue(double saldo){
        return contaRepository.findBySaldoLessThanEqualOrderByNumeroConta(saldo);
    }
}