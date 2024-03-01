import org.hibernate.dialect.function.LengthFunction;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MetodosInput {
    public int verificaId(Scanner input){
        boolean codValido = false;
        int codEmpr=-1;
        while (!codValido) {
            try {
                System.out.println("Digite o código do empregado: ");
                codEmpr = input.nextInt();
                String codString = String.valueOf(codEmpr);
                if(codString.length() != 4){
                    throw new NumberFormatException();
                }
                if(codEmpr<0){
                    throw new Exception();
                }
                codValido = true;
            } catch (InputMismatchException i) {
                System.out.println("Digite apenas números 🔢");
            }catch(NumberFormatException r){
                System.out.println("O código do empregado deve ter 4 digitos ❌");
            }catch (Exception e){
                System.out.println("Digite um código positivo ➕");
            }
            input.nextLine();
        }
        return codEmpr;
    }

    public double verificarSalario(Scanner input){
        DecimalFormat deci = new DecimalFormat("#.n##");
        boolean salarioValido = false;
        double salario =-1;
        double salarioFormatado =-1.0;
        while (!salarioValido) {
            try {
                System.out.println("Digite o salário do empregado: ");
                salario = input.nextDouble();
                if(salario < 0 || salario >9999999.99){
                    throw new Exception();
                }
                String salarioString = deci.format(salario);
                salarioFormatado = Double.valueOf(salarioString);
                salarioValido = true;
                System.out.println(salarioFormatado);
            }catch (InputMismatchException i) {
                System.out.println("Digite apenas números 🔢");
            }catch (Exception e){
                System.out.println("Digite um salário positivo e menor que um milhão ➕💵");
            }
            input.nextLine();
        }
        return salarioFormatado;
    }
    public Float verificarComissao(Scanner input){
        DecimalFormat deci = new DecimalFormat("#.##");
        boolean comissaoValida = false;
        Float comissao = 0.0F;
        Float comissaoFormatada = 0.0F;
        while (!comissaoValida) {
            try {
                System.out.println("Digite a comissão do empregado: ");
                comissao = input.nextFloat();
                if(comissao < 0 || comissao >9999999.99){
                    throw new Exception();
                }
                String comissaoString = deci.format(comissao);
                comissaoFormatada = Float.valueOf(comissaoString);
                comissaoValida = true;
            } catch (InputMismatchException i) {
                System.out.println("Digite apenas números 🔢");
            }catch (Exception e){
                System.out.println("Digite uma comissão positiva e menor que um milhão ➕💵");
            }
            input.nextLine();
        }
        return comissaoFormatada;
    }

    public int verificarDepartamento(Scanner input){
        boolean departamentoValido = false;
        int departamento = -1;
        while (!departamentoValido) {
            try {
                System.out.println("Digite o código do departamento do empregado: ");
                departamento = input.nextInt();
                String departamentoVerificado = String.valueOf(departamento);
                if(departamentoVerificado.length() != 2){
                    throw new RuntimeException();
                }
                if(departamento < 0){
                    throw new Exception();
                }
                departamentoValido = true;
            } catch (InputMismatchException i) {
                System.out.println("Digite apenas números 🔢");
            }catch(RuntimeException r){
                System.out.println("O código do departamente deve ter somente 2 números ❌");
            }
            catch (Exception e){
                System.out.println("Digite um departamento positivo ➕");
            }
            input.nextLine();
        }
        return departamento;
    }

    public int[] verificarData(Scanner input){
        boolean datValida = false;
        int[] data= new int[3];
        int dia=0;
        int mes=0;
        int ano=0;
        while (!datValida) {
            try {
                System.out.println("Digite o dia de contratação: ");
                dia = input.nextInt();
                data[0] = dia;
                System.out.println("Digite o mês de contratação: ");
                mes = input.nextInt();
                mes -= 1;
                data[1] = mes;
                System.out.println("Digite o ano de contratação: ");
                ano = input.nextInt();
                ano -= 1900;
                if( dia<0 ||mes<0||ano<0){
                    throw new Exception();
                }
                data[2] = ano;
                datValida = true;
            } catch (InputMismatchException i) {
                System.out.println("Digite apenas números 🔢");
            }catch (Exception e){
                System.out.println("Digite apenas dias, meses e anos positivos ➕");
            }
            //input.nextLine();
        }
        return data;
    }

    public Float verificarGerente(Scanner input, int codEmpr){
        boolean geranteValido = false;
        Float gerente =0.0F;
        while (!geranteValido) {
            try {
                System.out.println("Digite o código do gerente: ");
                gerente = input.nextFloat();
                if(gerente < 999 || gerente >9999){
                    throw new RuntimeException();
                }
                if(gerente==codEmpr){
                    throw new NullPointerException();
                }
                if(gerente<0){
                    throw new Exception();
                }
                geranteValido = true;
            }catch(NullPointerException n) {
                System.out.println("Você não pode ser seu próprio gerente ❌");
            }catch (InputMismatchException i) {
                System.out.println("Digite apenas números 🔢");
            }catch(RuntimeException r){
                System.out.println("O código do gerente deve ter 4 números ❌");
            }catch (Exception e){
                System.out.println("Digite um código positivo ➕");
            }
            //input.nextLine();
        }
        return gerente;
    }

    public String verificarNome(Scanner input){
        boolean nomeValido = false;
        String nome =" ";
        while (!nomeValido) {
            try {
                System.out.println("Digite o nome do empregado: ");
                nome = input.nextLine();
                if(nome.length() < 3 || nome.length() > 10){
                    throw new Exception();
                }
                nomeValido = true;
            }catch (Exception e){
                System.out.println("O nome deve ser maior que 2 caracteres e menor que 11 ❌");
            }
            //input.nextLine();
        }
        return nome;
    }

    public String verificarTrabalho(Scanner input){
        boolean trabalhoValido = false;
        String trabalho =" ";
        while (!trabalhoValido) {
            try {
                System.out.println("Digite o trabalho do empregado: ");
                trabalho = input.nextLine();
                if(trabalho.length() < 3 || trabalho.length() > 9){
                    throw new Exception();
                }
                trabalhoValido = true;
            }catch (Exception e){
                System.out.println("O trabalho deve ser maior que 2 caracteres e menor que 10 ❌");
            }
            //input.nextLine();
        }
        return trabalho;
    }
}
