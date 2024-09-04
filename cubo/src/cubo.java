import java.util.Scanner;

public class cubo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        int numCubos = n*n;
        int numCubos2 = numCubos*n;

        int semFace = numCubos2/8;
        int umaFace =numCubos2/2-8;
        int duasFaces =numCubos2/2-8;
        int tresFaces =8;

        System.out.println(semFace);
        System.out.println(umaFace);
        System.out.println(duasFaces);
        System.out.println(tresFaces);

    }
}