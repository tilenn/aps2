import java.util.Arrays;
import java.util.Scanner;

public class prog {
    public static void pogrezni(int[] a, int i, int dolzKopice) {
        // TODO
        // child nodes are at index (i * 2) + 1 and (i * 2) + 2

        // test

        if (dolzKopice == 1) {
            return;
        }

        // test

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = { 9, 8, 7, 6, 5, 1, 3, 2, 4 };

        for (int i = 0; i < 9; i++) {
            System.out.println(Arrays.toString(arr));

            int tmp = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = arr[0];
            arr[0] = tmp;

            pogrezni(arr, 0, arr.length - i - 1);
        }
        // int n = sc.nextInt();
        // int[] nums = new int[n];

        // for (int i = 0; i < n; i++) {
        // nums[i] = sc.nextInt();
        // }

        sc.close();
    }
}