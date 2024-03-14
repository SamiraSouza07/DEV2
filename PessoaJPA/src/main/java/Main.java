import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        Scanner input = new Scanner(System.in);
        PessoaDAO pessoa = new PessoaDAO();
        String[] campos = {"nome","idade","altura","peso","genero","estado","musica","cinema","leitura","esportes"};
        pessoa.iniciar();
        boolean opcaoValida = false;
        int opcao=0;
        while(!opcaoValida) {
            try {
                System.out.print("Digite uma opção:\n[1] - inserir\n[2] - Alterar\n[3] - Consultar\n[4] - Excluir\n--> ");
                opcao = input.nextInt();
                if (opcao > 4 || opcao < 1) {
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
                System.out.print("Escolha a informação que você deseja alterar" +
                        "\n[1] - Nome" +
                        "an[2] - Idade" +
                        "\n[3] - Altura" +
                        "\n[4] - Peso" +
                        "\n[5] - Gênero" +
                        "\n[6] - Estado" +
                        "\n[7] - Música" +
                        "\n[8] - Cinema" +
                        "\n[9] - Leitura" +
                        "\n[10] - Esportes" +
                        "\n--> ");
                int opcaoAlterar = input.nextInt();
                System.out.println("Digite o novo valor do campo "+campos[opcaoAlterar-1]+": ");
                String novoValor = input.nextLine();
                int alterou = pessoa.alterar(id,campos[opcaoAlterar],novoValor);
                if(alterou==1){
                    System.out.println("Campo alterado com sucesso ✅");
                }else{
                    System.out.println("Erro ao alterar campo ❌");
                }
            }
            case 3 ->{
                System.out.println("-----CONSULTAR-----");
            }
            case 4 ->{
                System.out.println("-----EXCLUIR-----");

            }
        }


}
}
