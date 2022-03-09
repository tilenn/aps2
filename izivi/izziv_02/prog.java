import java.util.Arrays;
import java.util.Scanner;

public class prog {
    public static void zgradiKopico(int[] a) {
        for (int i = a.length - 1; i >= 0; i--) {
            pogrezni(a, i, a.length);
        }
    }

    public static void pogrezni(int[] a, int i, int dolzKopice) {
        if (dolzKopice == 1) {
            return;
        }

        int lIndex = (i * 2) + 1;
        int rIndex = (i * 2) + 2;

        boolean l = lIndex < dolzKopice ? true : false;
        boolean r = rIndex < dolzKopice ? true : false;

        if (l && r) {
            if (a[lIndex] > a[rIndex] && a[lIndex] > a[i]) {
                int tmp = a[lIndex];
                a[lIndex] = a[i];
                a[i] = tmp;
                pogrezni(a, lIndex, dolzKopice);
            } else if (a[lIndex] < a[rIndex] && a[rIndex] > a[i]) {
                int tmp = a[rIndex];
                a[rIndex] = a[i];
                a[i] = tmp;
                pogrezni(a, rIndex, dolzKopice);
            }
        } else if (l && !r) {
            if (a[lIndex] > a[i]) {
                int tmp = a[lIndex];
                a[lIndex] = a[i];
                a[i] = tmp;
                pogrezni(a, lIndex, dolzKopice);
            }
        } else if (!l && r) {
            if (a[rIndex] > a[i]) {
                int tmp = a[rIndex];
                a[rIndex] = a[i];
                a[i] = tmp;
                pogrezni(a, rIndex, dolzKopice);
            }
        }
    }

    public static void printKopica(int[] a, int len) {
        if (len == 1) {
            System.out.println(a[0]);
            return;
        }
        int x = 1;
        int c = 0;
        for (int i = 0; i < len; i++) {
            if (c < x) {
                System.out.print((i == 0 ? "" : " ") + a[i]);
            }
            c++;
            if (c == x) {
                System.out.print(" |");
                c = 0;
                x = x * 2;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        zgradiKopico(arr);
        printKopica(arr, arr.length);
        for (int i = 0; i < arr.length - 1; i++) {
            int tmp = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = arr[0];
            arr[0] = tmp;

            pogrezni(arr, 0, arr.length - i - 1);
            printKopica(arr, arr.length - i - 1);
        }
        sc.close();
    }
}