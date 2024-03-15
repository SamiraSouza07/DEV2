import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        Scanner input = new Scanner(System.in);
        PessoaDAO pessoa = new PessoaDAO();
        pessoa.iniciar();
        String[] campos = {"nome","idade","altura","peso","genero","estado","musica","cinema","leitura","esportes"};
        boolean opcaoValida = false;
        int opcao=0;
        while(!opcaoValida) {
            try {
                System.out.print("Digite uma opção:\n[0] - Sair\n[1] - inserir\n[2] - Alterar\n[3] - Consultar\n[4] - Excluir\n--> ");
                opcao = input.nextInt();
                if (opcao > 4 || opcao < 0) {
                    throw new Exception();
                }
                opcaoValida=true;
            } catch (InputMismatchException i) {
                System.out.println("Digite apenas números");
            } catch (Exception e) {
                System.out.println("Digite apenas opções do menu");
            }
            input.nextLine();
        }
        switch (opcao){
            case 1 ->{
                System.out.println("-----INSERIR-----");
                System.out.println("Digite o id da pessoa: ");
                int id = input.nextInt();
                input.nextLine();
                System.out.println("Digite o nome da pessoa: ");
                String nome = input.nextLine();
                System.out.println("Digite a idade da pessoa: ");
                int idade = input.nextInt();
                System.out.println("Digite a altura da pessoa: ");
                double altura = input.nextDouble();
                System.out.println("Digite o peso da pessoa: ");
                double peso = input.nextDouble();
                input.nextLine();
                System.out.println("Digite o genêro da pessoa: ");
                String genero = input.nextLine();
                System.out.println("Digite o estado da pessoa: ");
                String estado = input.nextLine();
                System.out.println("Digite se a pessoa gosta de música(s/n): ");
                int musica=0;
                if(input.next().equalsIgnoreCase("s")){
                    musica = 1;
                }
                System.out.println("Digite se a pessoa gosta de cinema(s/n): ");
                int cinema=0;
                if(input.next().equalsIgnoreCase("s")){
                    cinema = 1;
                }
                System.out.println("Digite se a pessoa gosta de leitura(s/n): ");
                int leitura=0;
                if(input.next().equalsIgnoreCase("s")){
                    leitura = 1;
                }
                System.out.println("Digite se a pessoa gosta de esportes(s/n): ");
                int esportes=0;
                if(input.next().equalsIgnoreCase("s")){
                    esportes = 1;
                }
                int inserir = pessoa.inserir(id,nome,idade,altura,peso,genero,estado,musica,cinema,leitura,esportes);
                if(inserir==1){
                    System.out.println("Pessoa inserida com sucesso");
                }else {
                    System.out.println("Erro inserir a pessoa");
                }
            }
            case 2 ->{
                System.out.println("-----ALTERAR-----");
                System.out.println("Digite o id da pessoa: ");
                int id = input.nextInt();
                input.nextLine();
                boolean opcaoCampo = false;
                int opcaoAlterar=0;
                while(!opcaoCampo) {
                    try {
                        System.out.print("Escolha a informação que você deseja alterar" +
                                "\n[1] - Nome" +
                                "\n[2] - Idade" +
                                "\n[3] - Altura" +
                                "\n[4] - Peso" +
                                "\n[5] - Gênero" +
                                "\n[6] - Estado" +
                                "\n[7] - Música" +
                                "\n[8] - Cinema" +
                                "\n[9] - Leitura" +
                                "\n[10] - Esportes" +
                                "\n--> ");
                        opcaoAlterar = input.nextInt();
                        if (opcao > 10 || opcao < 1) {
                            throw new Exception();
                        }
                        opcaoCampo=true;
                    } catch (InputMismatchException i) {
                        System.out.println("Digite apenas números");
                    } catch (Exception e) {
                        System.out.println("Digite apenas opções do menu");
                    }
                    input.nextLine();
                }
                System.out.println("Digite o novo valor do campo "+campos[opcaoAlterar-1]+": ");
                String novoValor = input.nextLine();
                int alterou = pessoa.alterar(id,campos[opcaoAlterar],novoValor);
                if(alterou==1){
                    System.out.println("Campo alterado com sucesso ✅");
                }else if(alterou==0){
                    System.out.println("Essa pessoa não existe ❌");
                }else{
                    System.out.println("Erro ao alterar campo ❌");
                }
            }
            case 3 ->{
                System.out.println("-----CONSULTAR-----");
                boolean consulta = false;
                int opcaoConsulta=0;
                while(!consulta) {
                    try {
                        System.out.print("Escolha a partir de qual informação você deseja consultar" +
                                "\n[0] - Nenhuma (mostrar todos)"+
                                "\n[1] - Nome" +
                                "\n[2] - Idade" +
                                "\n[3] - Altura" +
                                "\n[4] - Peso" +
                                "\n[5] - Gênero" +
                                "\n[6] - Estado" +
                                "\n[7] - Música" +
                                "\n[8] - Cinema" +
                                "\n[9] - Leitura" +
                                "\n[10] - Esportes" +
                                "\n--> ");
                        opcaoConsulta = input.nextInt();
                        if (opcao > 10 || opcao < 1) {
                            throw new Exception();
                        }
                        consulta=true;
                    } catch (InputMismatchException i) {
                        System.out.println("Digite apenas números");
                    } catch (Exception e) {
                        System.out.println("Digite apenas opções do menu");
                    }
                    input.nextLine();
                }
                List pessoas = null;
                if(opcaoConsulta==0){
                    pessoas = pessoa.buscarPorFiltro("todos","nenhum");
                }else {
                    System.out.print("Digite o valor que você deseja buscar do campo "+campos[opcaoConsulta-1]+"\n--> ");
                    String valorFiltro = input.nextLine();
                    pessoas = pessoa.buscarPorFiltro(campos[opcaoConsulta-1],valorFiltro);
                }
                System.out.println("----PESSOAS----");
                for(Object pss:pessoas){
                    System.out.println(pss);
                }
            }
            case 4 ->{
                System.out.println("-----EXCLUIR-----");
                System.out.println("Digite o código da pessoa a ser excluida: ");
                int id = input.nextInt();
                int excluir = pessoa.excluir(id);
                if(excluir==2){
                    System.out.println("Você não pode excluir essa pessoa ❌");
                }else if(excluir==-1){
                    System.out.println("Erro ao excluir pessoa ❌");
                }else if(excluir==0){
                    System.out.println("Essa pessoa não existe ❌");
                }else{
                    System.out.println("Pessoa excluida com sucesso ✅");
                }

            }
            default -> {
                System.out.println("Saindo do programa....");
            }
        }
        pessoa.fechar();
    }
}
