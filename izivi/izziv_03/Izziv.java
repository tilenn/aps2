import java.util.Scanner;

public class Izziv {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] a = new int[n];
        int[] b = new int[m];
        int[] merged = new int[n + m];

        // preberemo v vsako tabelo posebi
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
        }

        // System.out.println("Tabela a: " + Arrays.toString(a));
        // System.out.println("Tabela b: " + Arrays.toString(b));

        int x = 0;
        int y = 0;

        // dokler ne predelamo obeh tabel
        while (x != a.length || y != b.length) {
            if (x != a.length && y != b.length) {
                int aa = a[x];
                int bb = b[y];

                if (bb < aa) {
                    merged[x + y] = bb;
                    System.out.print("b");
                    y++;
                } else {
                    merged[x + y] = aa;
                    System.out.print("a");
                    x++;
                }
            } else if (x == a.length) {
                merged[x + y] = b[y];
                System.out.print("b");
                y++;
            } else {
                merged[x + y] = a[x];
                System.out.print("a");
                x++;
            }
        }

        System.out.println();
        // System.out.println(Arrays.toString(merged));

        for (int i = 0; i < merged.length; i++) {
            System.out.print(merged[i] + (i == merged.length - 1 ? "" : " "));
        }

        sc.close();
    }
}
