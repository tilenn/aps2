
// import java.util.Arrays;
import java.util.Scanner;

public class Izziv {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int vrstice = sc.nextInt();
        int stolpci = sc.nextInt();

        int[][] tabela = new int[vrstice + 1][stolpci];

        for (int i = 0; i < tabela.length; i++) {
            if (i < 3) {
                for (int j = 0; j < tabela[i].length; j++) {
                    tabela[i][j] = i;
                }
            } else {
                tabela[i][0] = i;
                // racunamo vrednosti
                for (int j = 1; j < tabela[i].length; j++) {
                    // racunamo tabela[i][j]
                    // gledamo (j-1)-ti in j-ti stolpec
                    int tmp = Integer.MAX_VALUE;
                    for (int k = 0; k < i; k++) {
                        int ena = tabela[k][j - 1];
                        int dva = tabela[i - 1 - k][j];
                        tmp = Math.min(Math.max(ena, dva), tmp);
                    }

                    tabela[i][j] = tmp + 1;
                }
            }
        }

        // for (int[] row : tabela) {
        // System.out.println(Arrays.toString(row));
        // }

        System.out.print("    ");
        for (int i = 1; i <= stolpci; i++) {
            System.out.printf("%4d", i);
        }
        System.out.println();
        for (int i = 0; i < tabela.length; i++) {
            System.out.printf("%4d", i);
            for (int j = 0; j < tabela[i].length; j++) {
                System.out.printf("%4d", tabela[i][j]);
            }
            System.out.println();
        }

        sc.close();
    }
}