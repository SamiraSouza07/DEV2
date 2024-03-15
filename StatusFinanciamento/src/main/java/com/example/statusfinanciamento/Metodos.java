package com.example.statusfinanciamento;

public class Metodos {
    public double calcularImpostos(double salarioBruto){
        double aliquotaINSS = 0;
        double deducaoINSS = 0;
        double aliquotaIRPF = 0;
        double deducaoIRPF = 0;
        if(salarioBruto <= 1412.00){
            aliquotaINSS=7.5;
            deducaoINSS=0;
        }else if(salarioBruto>=1412.01 && salarioBruto<=2666.68){
            aliquotaINSS=9;
            deducaoINSS=21.18;
        }else if(salarioBruto>=1666.69 && salarioBruto<=4000.03){
            aliquotaINSS=12;
            deducaoINSS=101.18;
        }else if(salarioBruto>=4000.04 && salarioBruto<=778602){
            aliquotaINSS=14;
            deducaoINSS=181.18;
        }
        double INSS = ((salarioBruto*aliquotaINSS)/100)-deducaoINSS;
        double descontoINSS = salarioBruto-INSS;
        if(descontoINSS<=2259.20){
            aliquotaIRPF=1;
            deducaoIRPF=0;
        }else if(descontoINSS>= 2259.20 && descontoINSS <=2826.65){
            aliquotaIRPF=7.5;
            deducaoIRPF=169.44;
        }else if(descontoINSS>=2826.66 && descontoINSS<= 3751.05){
            aliquotaIRPF=15;
            deducaoIRPF=381.44;
        }else if(descontoINSS>=3751.06 && descontoINSS<=4664.68){
            aliquotaIRPF=22.5;
            deducaoIRPF=662.77;
        }else if(descontoINSS>=4664.69){
            aliquotaIRPF=27.5;
            deducaoIRPF=896.00;
        }
        double IRPF = ((descontoINSS*aliquotaIRPF)/100)-deducaoIRPF;
        double novoSalario = salarioBruto-INSS-IRPF;
        return novoSalario;
    }
}
