import java.text.DecimalFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //Criando os objetos da classe EmpregaDAO, DepartamentoDAO e Scanner
        EmpregadoDAO emp = new EmpregadoDAO();
        DepartamentoDAO dept = new DepartamentoDAO();
        MetodosInput metodo = new MetodosInput();
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
                //case para inserir empregado
                case 1 -> {
                    //declaração de variaveis
                    int codEmpr = 0;
                    String nome;
                    String trabalho;
                    Float gerente = 0.0F;
                    double salario = 0;
                    Float comissao = 0.0F;
                    int departamento = 0;

                    //input das informações
                    System.out.println("----INSERIR EMPREGADO----");
                    codEmpr = metodo.verificaId(input);
                    nome = metodo.verificarNome(input);
                    trabalho = metodo.verificarTrabalho(input);
                    gerente = metodo.verificarGerente(input,codEmpr);
                    int[] data= metodo.verificarData(input);
                    salario = metodo.verificarSalario(input);
                    comissao = metodo.verificarComissao(input);
                    departamento = metodo.verificarDepartamento(input);

                    //inserindo empregado
                    boolean inserir = emp.inserirEmp(codEmpr,nome.toUpperCase(), trabalho.toUpperCase(), gerente,new Date(data[0],data[1],data[2]), salario, comissao, departamento);
                    //if para ver se deu certo
                    if(inserir){
                        System.out.println("Empregado "+nome+" inserido com sucesso ✅");
                    }else{
                        System.out.println("Erro ao inserir o empregado ❌");
                    }

                }
                //case para alterar o nome do empregado
                case 2 ->{
                    System.out.println("----ALTERAR NOME----");
                    int codEmpr=0;
                    String nome;
                    codEmpr = metodo.verificaId(input);
                    nome = metodo.verificarNome(input);
                    int alterarNome = emp.alterarNome(codEmpr,nome);
                    if (alterarNome==1){
                        System.out.println("Nome alterado com sucesso ✅, olá! "+nome);
                    }else if(alterarNome == 2){
                        System.out.println("Este empregado não existe ❌");
                    }
                    else{
                        System.out.println("Erro ao alterar o nome ❌");
                    }
                }
                case 3 ->{
                    System.out.println("----ALTERAR SALÁRIO----");
                    boolean codValido = false;
                    int codEmpr=0;
                    double salario=0;
                    codEmpr = metodo.verificaId(input);
                    salario = metodo.verificarSalario(input);
                    int alterarSalario = emp.alterarSalario(codEmpr,salario);
                    if(alterarSalario==1){
                        System.out.println("Salário alterado com sucesso ✅");
                    }else if(alterarSalario==2){
                        System.out.println("Este empregado não existe ❌");
                    }
                    else{
                        System.out.println("Erro ao alterar salário ❌");
                    }

                }
                case 4 ->{
                    System.out.println("----EXCLUIR EMPREGADO----");
                    int codEmpr=0;
                    codEmpr = metodo.verificaId(input);
                    boolean simOuNao = false;
                    while(!simOuNao){
                        System.out.println("Deseja realmente excluir o empregado este empregado (S/N)? --> ");
                        String escolha = input.next();
                        if(escolha.equalsIgnoreCase("N")){
                            System.out.println("Ok");
                            simOuNao = true;
                        }else{
                            if(escolha.equalsIgnoreCase("S")){
                                int excluir = emp.excluirEmp(codEmpr);
                                simOuNao = true;
                                if (excluir == 1){
                                    System.out.println("Empregado removido com sucesso ✅");
                                }else if(excluir==0){
                                    System.out.println("Este empregado não existe ou ele é gestor de alguêm ❌");
                                }else if(excluir==-1){
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
                    int codEmpr=0;
                    codEmpr = metodo.verificaId(input);
                    Empregado emp1 = emp.buscarPorCod(codEmpr);
                    if(emp1 == null){
                        System.out.println("Este empregado não existe ❌");
                    }else {
                        System.out.println(emp1);
                    }
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
