import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Conectar coneccao = new Conectar();
        Scanner input = new Scanner(System.in);
        int opcao = 1;
        while (opcao != 0) {
            System.out.print("Escolha uma opção:"+
                    "\n[1] - Inserir"+
                    "\n[2] - Alterar"+
                    "\n[3] - Excluir"+
                    "\n[4] - Consultar"+
                    "\n-> ");
            opcao = input.nextInt();
            switch (opcao) {
                case 1 -> {
                    System.out.println("Inserir customer");
                    System.out.println("Custid: ");
                    int custid = input.nextInt();
                    System.out.println("Name: ");
                    input.nextLine();
                    String name = input.nextLine();
                    System.out.println("Address: ");
                    String address = input.next();
                    System.out.println("City: ");
                    String city = input.next();
                    System.out.println("State: ");
                    String state = input.next();
                    System.out.println("Zip: ");
                    String zip = input.next();
                    System.out.println("Area: ");
                    int area = input.nextInt();
                    System.out.println("Phone: ");
                    String phone = input.next();
                    System.out.println("Repid: ");
                    int repid = input.nextInt();
                    System.out.println("Creditlimit: ");
                    double creditlimit = input.nextDouble();
                    System.out.println("Comments: ");
                    String comments = input.next();
                    boolean certo = coneccao.inserir(custid, name, address, city, state, zip, area, phone, repid, creditlimit, comments);
                    if (certo) {
                        System.out.println("Customer inserido com sucesso");
                    } else {
                        System.out.println("Erro ao inserir customer");
                    }
                }
                case 2 -> {
                    System.out.println("Alterar no do customer");
                    System.out.println("Custid: ");
                    int custid = input.nextInt();
                    System.out.println("Digite o novo nome: ");
                    input.nextLine();
                    String novoValor = input.nextLine();
                    boolean certo = coneccao.alterar(custid, novoValor);
                    if (certo) {
                        System.out.println("Nome alterado com sucesso");
                    } else {
                        System.out.println("Erro ao alterar o nome");
                    }
                }
                case 3 -> {
                    System.out.println("Excluir customer");
                    System.out.println("Custid: ");
                    int custid = input.nextInt();
                    boolean certo = coneccao.excluir(custid);
                    if (certo) {
                        System.out.println("Customer excluido com sucesso");
                    } else {
                        System.out.println("Erro ao excluir custumer");
                    }
                }
                case 4 -> {
                    System.out.println("Consultar todos os customers");
                    ResultSet retornoConsulta = coneccao.consultar();
                    try {
                        if (!retornoConsulta.isBeforeFirst()) {
                            System.out.println("A consulta não retornou dados");
                        } else {
                            while (retornoConsulta.next()) {
                                String lista = ("\ncustid: " + retornoConsulta.getInt("custid") + "\n" + "name: "
                                        + retornoConsulta.getString("name") + "\n" + "address: " + retornoConsulta.getString("address") + "\n" + "city: " +
                                        retornoConsulta.getString("city") + "\n" + "state: " + retornoConsulta.getString("state") + "\n" + "zip: " + retornoConsulta.getString("zip") +
                                        "\n" + "area: " + retornoConsulta.getInt("area") + "\n" + "phone: " + retornoConsulta.getString("phone") +
                                        "\n" + "repid: " + retornoConsulta.getInt("repid") + "\n" + "creditlimit: " + retornoConsulta.getDouble("creditlimit") +
                                        "\n" + "comments: " + retornoConsulta.getString("comments"));
                                System.out.println(lista);
                            }
                            System.out.println("\n");
                        }
                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                    }
                }
            }
        }
        System.out.println("Saindo");

    }
}