import java.text.DecimalFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        EmpregadoDAO emp = new EmpregadoDAO();
        DepartamentoDAO dept = new DepartamentoDAO();
        Scanner input = new Scanner(System.in);
        emp.iniciar();
        dept.iniciar();

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
                } catch (Exception e) {
                    System.out.println("Digite apenas números que estão no menu 🔢");
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
                    boolean codValido = false;
                    while (!codValido) {
                        try {
                            System.out.println("Digite o código do empregado: ");
                            codEmpr = input.nextInt();
                            if(codEmpr<0){
                                throw new Exception();
                            }
                            if(emp.buscarPorCod(codEmpr) != null){
                                throw new RuntimeException();
                            }
                            codValido = true;
                        } catch (InputMismatchException i) {
                            System.out.println("Digite apenas números 🔢");
                        }catch(RuntimeException r){
                            System.out.println("Já existe um empregado com este código ❌");
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
                            System.out.println("Digite o código do gerente abaixo");
                            System.out.println("-----GERENTES-----");
                            List empregados = emp.consultarTodosCodigos();
                            for(Object cod :empregados){
                                System.out.println("Cód.: "+cod+" Nome: "+emp.buscarPorCod((Integer) cod).getNome());
                            }
                            System.out.println("Código do gerente: ");
                            gerente = input.nextFloat();
                            if(gerente<0){
                                throw new Exception();
                            }
                            DecimalFormat deci = new DecimalFormat("0");
                            String verifGerente = deci.format(gerente);
                            int codGerente = Integer.parseInt(verifGerente);
                            if(emp.buscarPorCod(codGerente) == null){
                                throw new RuntimeException();
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
                            System.out.println("Digite apenas números 🔢");
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
                        }catch (Exception e){
                            System.out.println("Digite uma comissão positiva ➕");
                        }
                        input.nextLine();
                    }
                    boolean departamentoValido = false;
                    while (!departamentoValido) {
                        try {
                            System.out.println("Digite o código do departamento do empregado abaixo");
                            System.out.println("----DEPARTAMENTOS----");
                            List depts = dept.consultarDept();
                            for(Object de:depts){
                                System.out.println(de);
                            }
                            System.out.println("Código do departamento: ");
                            departamento = input.nextInt();
                            if(departamento < 0){
                                throw new Exception();
                            }
                            if(dept.buscarPorCod(departamento) == null){
                                throw new RuntimeException();
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
                    List<Empregado> empregados = emp.buscarPorTrabalho(trabalho);
                    for(Object empregado:empregados){
                        System.out.println("\n"+empregado);
                    }
                }
                default ->{
                    System.out.println("Encerrando o programa 🏃‍♂️...");
                }
            }
        }while (opcao!=0);
        dept.encerrar();
        emp.encerrar();
    }
}
