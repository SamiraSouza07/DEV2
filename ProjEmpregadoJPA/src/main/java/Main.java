import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        EmpregadoDAO emp = new EmpregadoDAO();
        Scanner input = new Scanner(System.in);
        emp.iniciar();
        int opcao = 1;
        do {
            boolean opcaoValida = false;
            while (!opcaoValida) {
                try {
                    System.out.print("\nEscolha uma opção: " +
                            "\n[0] - Sair 🚶‍♂️" +
                            "\n[1] - Inserir empregados ➕" +
                            "\n[2] - Alterar nome 📝" +
                            "\n[3] - Alterar salário 💵" +
                            "\n[4] - Excluir empregado 👨‍💼" +
                            "\n[5] - Consultar todos os empregados 👩‍💼👨‍🏭👩‍🍳👨‍⚖️" +
                            "\n[6] - Buscar empregado pelo código 👩‍💼" +
                            "\n[7] - Buscar empregados pelo trabalho 👩‍💼👨‍💼🧑‍💼" +
                            "\n--> ");
                    opcao = input.nextInt();
                    if (opcao > 7 || opcao < 0) {
                        throw new Exception();
                    }
                    opcaoValida = true;
                } catch (InputMismatchException i) {
                    System.out.println("Digite apenas números 🔢");
                    opcaoValida = false;
                } catch (Exception e) {
                    System.out.println("Digite apenas números que estão no menu 🔢");
                    opcaoValida = false;
                }
                input.nextLine();
            }
            switch (opcao){
                case 1 -> {
                    int codEmpr = 0;
                    String nome;
                    Float gerente = 0.0F;
                    int dia = 0;
                    int mes = 0;
                    int ano = 0;
                    double salario = 0;
                    Float comissao = 0.0F;
                    int departamento = 0;
                    System.out.println("----INSERIR EMPREGADO----");
                    //input.nextLine();
                    boolean codValido = false;
                    while (!codValido) {
                        try {
                            System.out.println("Digite o código do empregado: ");
                            codEmpr = input.nextInt();
                            if(codEmpr<0){
                                throw new Exception();
                            }
                            codValido = true;
                        } catch (InputMismatchException i) {
                            System.out.println("Digite apenas números 🔢");
                            codValido = false;
                        }catch (Exception e){
                            System.out.println("Digite um código positivo ➕");
                        }
                        input.nextLine();
                    }
                    System.out.println("Digite o nome do empregado: ");
                    nome = input.nextLine();
                    System.out.println("Digite o trabalho do empregado: ");
                    String trabalho = input.nextLine();
                    boolean geranteValido = false;
                    while (!geranteValido) {
                        try {
                            System.out.println("Digite código do gerente: ");
                            gerente = input.nextFloat();
                            if(gerente<0){
                                throw new Exception();
                            }
                            geranteValido = true;
                        } catch (InputMismatchException i) {
                            System.out.println("Digite apenas números 🔢");
                            geranteValido = false;
                        }catch (Exception e){
                            System.out.println("Digite um código positivo ➕");
                        }
                        input.nextLine();
                    }
                    boolean datValida = false;
                    while (!datValida) {
                        try {
                            System.out.println("Digite o dia de contratação: ");
                            dia = input.nextInt();
                            System.out.println("Digite o mês de contratação: ");
                            mes = input.nextInt();
                            System.out.println("Digite o ano de contratação: ");
                            ano = input.nextInt();
                            if( dia<0 ||mes<0||ano<0){
                                throw new Exception();
                            }
                            datValida = true;
                        } catch (InputMismatchException i) {
                            System.out.println("Digite apenas números 🔢");
                            datValida = false;
                        }catch (Exception e){
                            System.out.println("Digite apenas dias, meses e anos positivos ➕");
                        }
                        input.nextLine();
                    }
                    boolean salarioValido = false;
                    while (!salarioValido) {
                        try {
                            System.out.println("Digite o salário do empregado: ");
                            salario = input.nextDouble();
                            if(salario < 0){
                                throw new Exception();
                            }
                            salarioValido = true;
                        } catch (InputMismatchException i) {
                            System.out.println("Digite apenas números 💵");
                            salarioValido = false;
                        }catch (Exception e){
                            System.out.println("Digite um salário positivo ➕");
                        }
                        input.nextLine();
                    }
                    boolean comissaoValida = false;
                    while (!comissaoValida) {
                        try {
                            System.out.println("Digite a comissão do empregado: ");
                            comissao = input.nextFloat();
                            if(comissao < 0){
                                throw new Exception();
                            }
                            comissaoValida = true;
                        } catch (InputMismatchException i) {
                            System.out.println("Digite apenas números 🔢");
                            comissaoValida = false;
                        }catch (Exception e){
                            System.out.println("Digite uma comissão positiva ➕");
                        }
                        input.nextLine();
                    }
                    boolean departamentoValido = false;
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
                            departamentoValido = false;
                        }catch (Exception e){
                            System.out.println("Digite um departamento positivo ➕");
                        }
                        input.nextLine();
                    }
                    boolean inserir = emp.inserirEmp(codEmpr,nome.toUpperCase(), trabalho.toUpperCase(), gerente,new Date(ano,mes,dia), salario, comissao, departamento);
                    if(inserir){
                        System.out.println("Empregado "+nome+" inserido com sucesso ✅");
                    }else{
                        System.out.println("Erro ao inserir o empregado ❌");
                    }

                }
                case 2 ->{
                    System.out.println("----ALTERAR NOME----");
                    //input.nextLine();
                    boolean codValido = false;
                    int codEmpr=0;
                    String nome;
                    while (!codValido) {
                        try {
                            System.out.println("Digite o código do empregado: ");
                            codEmpr = input.nextInt();
                            if(codEmpr<0){
                                throw new Exception();
                            }
                            if(emp.buscarPorCod(codEmpr) == null){
                                throw new NullPointerException();
                            }
                            codValido = true;
                        } catch (InputMismatchException i) {
                            System.out.println("Digite apenas números 🔢");
                            codValido = false;
                        }catch (NullPointerException n){
                            System.out.println("Não existe um empregado com este código ❌");
                        }catch (Exception e){
                            System.out.println("Digite um código positivo ➕");
                        }
                        input.nextLine();
                    }
                    System.out.println("Digite o novo nome do empregado: ");
                    nome = input.nextLine();
                    boolean alterarNome = emp.alterarNome(codEmpr,nome);
                    if (alterarNome){
                        System.out.println("Nome alterado com sucesso ✅, olá! "+nome);
                    }else{
                        System.out.println("Erro ao alterar o nome ❌");
                    }
                }
                case 3 ->{
                    System.out.println("----ALTERAR SALÁRIO----");
                    //input.nextLine();
                    boolean codValido = false;
                    int codEmpr=0;
                    double salario=0;
                    while (!codValido) {
                        try {
                            System.out.println("Digite o código do empregado: ");
                            codEmpr = input.nextInt();
                            if(codEmpr<0){
                                throw new Exception();
                            }
                            if(emp.buscarPorCod(codEmpr) == null){
                                throw new NullPointerException();
                            }
                            codValido = true;
                        } catch (InputMismatchException i) {
                            System.out.println("Digite apenas números 🔢");
                            codValido = false;
                        }catch (NullPointerException n){
                            System.out.println("Não existe um empregado com este código ❌");
                        }catch (Exception e){
                            System.out.println("Digite um código positivo ➕");
                        }
                        input.nextLine();
                    }
                    boolean salarioValido = false;
                    while (!salarioValido) {
                        try {
                            System.out.println("Digite o novo salário do empregado: ");
                            salario = input.nextDouble();
                            if(salario<0){
                                throw new Exception();
                            }
                            salarioValido = true;
                        } catch (InputMismatchException i) {
                            System.out.println("Digite apenas números 🔢");
                            salarioValido = false;
                        }catch (Exception e){
                            System.out.println("Digite um salário positivo ➕");
                        }
                        input.nextLine();
                    }
                    boolean alterarSalario = emp.alterarSalario(codEmpr,salario);
                    if(alterarSalario){
                        System.out.println("Salário alterado com sucesso ✅");
                    }else{
                        System.out.println("Erro ao alterar salário ❌");
                    }

                }
                case 4 ->{
                    System.out.println("----EXCLUIR EMPREGADO----");
                    boolean codValido = false;
                    int codEmpr=0;
                    while (!codValido) {
                        try {
                            System.out.println("Digite o código do empregado: ");
                            codEmpr = input.nextInt();
                            if(codEmpr<0){
                                throw new Exception();
                            }
                            if(emp.buscarPorCod(codEmpr) == null){
                                throw new NullPointerException();
                            }
                            codValido = true;
                        } catch (InputMismatchException i) {
                            System.out.println("Digite apenas números 🔢");
                            codValido = false;
                        }catch (NullPointerException n){
                            System.out.println("Não existe um empregado com este código ❌");
                        }catch (Exception e){
                            System.out.println("Digite um código positivo ➕");
                        }
                        input.nextLine();
                    }
                    boolean simOuNao = false;
                    while(!simOuNao){
                        System.out.println("Deseja realmente excluir o empregado "+emp.buscarPorCod(codEmpr).getNome());
                        System.out.print("(S/N)? --> ");
                        String escolha = input.next();
                        if(escolha.equalsIgnoreCase("N")){
                            System.out.println("Ok");
                            simOuNao = true;
                        }else{
                            if(escolha.equalsIgnoreCase("S")){
                                boolean excluir = emp.excluirEmp(codEmpr);
                                simOuNao = true;
                                if (excluir){
                                    System.out.println("O empregado foi excluido com sucesso ✅");
                                }else {
                                    System.out.println("Erro ao excluir empregado ❌");
                                }
                            }
                            else{
                                System.out.println("Opção inválida ❌");
                            }
                        }

                    }
                }
                case 5 ->{
                    System.out.println("----CONSULTAR TODOS OS EMPREGADOS----");
                    List empregados = emp.consultarTodos();
                    for (Object empregado:empregados){
                        System.out.println("\n"+empregado);
                    }
                }
                case 6 ->{
                    System.out.println("----BUSCAR EMPREGADO PELO CÓDIGO----");
                    boolean codValido = false;
                    int codEmpr=0;
                    while (!codValido) {
                        try {
                            System.out.println("Digite o código do empregado: ");
                            codEmpr = input.nextInt();
                            if(codEmpr<0){
                                throw new Exception();
                            }
                            if(emp.buscarPorCod(codEmpr) == null){
                                throw new NullPointerException();
                            }
                            codValido = true;
                        } catch (InputMismatchException i) {
                            System.out.println("Digite apenas números 🔢");
                            codValido = false;
                        }catch (NullPointerException n){
                            System.out.println("Não existe um empregado com este código ❌");
                        }catch (Exception e){
                            System.out.println("Digite um código positivo ➕");
                        }
                        input.nextLine();
                    }
                    Empregado emp1 = emp.buscarPorCod(codEmpr);
                    System.out.println(emp1);
                }
                case 7 ->{
                    System.out.println("----BUSCAR EMPREGADOS PELO TRABALHO----");
                    System.out.println("Digite o trabalho dos clientes que você deseja procurar: ");
                    String trabalho = input.nextLine();
                    List empregados = emp.buscarPorTrabalho(trabalho);
                    for(Object empregado:empregados){
                        System.out.println("\n"+empregado);
                    }
                }
                default ->{
                    System.out.println("Encerrando o programa 🏃‍♂️...");
                }
            }
        }while (opcao!=0);
        emp.encerrar();
    }
}
