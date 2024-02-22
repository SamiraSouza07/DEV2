import org.hibernate.dialect.function.LengthFunction;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MetodosInput {
    public int verificaId(Scanner input){
        boolean codValido = false;
        int codEmpr=-1;
        while (!codValido) {
            try {
                System.out.println("Digite o código do empregado: ");
                String codString = String.valueOf(codEmpr);
                if(codString.length() != 4){
                    throw new NumberFormatException();
                }
                codEmpr = input.nextInt();
                if(codEmpr<0){
                    throw new Exception();
                }
                codValido = true;
            } catch (InputMismatchException i) {
                System.out.println("Digite apenas números 🔢");
            }catch(NumberFormatException r){
                System.out.println("O código do empregado deve ter quatro digitos ❌");
            }catch (Exception e){
                System.out.println("Digite um código positivo ➕");
            }
            input.nextLine();
        }
        return codEmpr;
    }

    public double verificarSalario(Scanner input){
        DecimalFormat deci = new DecimalFormat("#.##");
        boolean salarioValido = false;
        double salario =-1;
        Double salarioFormatado =-1.0;
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
                if(departamento < 0){
                    throw new Exception();
                }
                departamentoValido = true;
            } catch (InputMismatchException i) {
                System.out.println("Digite apenas números 🔢");
            }catch(RuntimeException r){
                System.out.println("Não há um departamento com este código ❌");
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
            input.nextLine();
        }
        return data;
    }

    public Float verificarGerente(Scanner input){
        boolean geranteValido = false;
        Float gerente =0.0F;
        while (!geranteValido) {
            try {
                System.out.println("Digite o código do gerente: ");
                gerente = input.nextFloat();
                if(gerente<0){
                    throw new Exception();
                }
                geranteValido = true;
            } catch (InputMismatchException i) {
                System.out.println("Digite apenas números 🔢");
            }catch(RuntimeException r){
                System.out.println("Não existe um gerente com este código ❌");
            }
            catch (Exception e){
                System.out.println("Digite um código positivo ➕");
            }
            input.nextLine();
        }
        return gerente;
    }
}
