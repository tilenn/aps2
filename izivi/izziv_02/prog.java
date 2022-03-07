import java.util.Scanner;

public class prog {
    public static void pogrezni(int[] a, int i, int dolzKopice) {
        // TODO
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        sc.close();
    }
}