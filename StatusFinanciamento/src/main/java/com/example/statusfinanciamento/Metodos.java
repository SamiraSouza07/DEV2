package com.example.statusfinanciamento;

public class Metodos {
    public double calcularINSS(double salarioBruto){
        double aliquota = 0;
        if(salarioBruto <= 1412.00){
            aliquota=7.5;
        }else if(salarioBruto>=1412.01 && salarioBruto<=2666.68){
            aliquota=9;
        }else if(salarioBruto>=1666.69 && salarioBruto<=4000.03){
            aliquota=12;
        }else if(salarioBruto>=4000.04 && salarioBruto<=778602){
            aliquota=14;
        }
        double novoSalario = (salarioBruto*aliquota)/100 +salarioBruto;
        return novoSalario;
    }
}
