import java.util.ArrayList;
import java.util.Scanner;

public class prog {
    public static void printZaporedje(int[] stevila, int l, int r, int val) {
        System.out.print("[");
        for (int i = l; i <= r; i++) {
            System.out.print(stevila[i]);
            if (i <= r - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]: " + val);
    }

    public static int divide(int[] stevila, int l, int r) {
        // imamo samo eno stevilo --> edge case
        if (l == r) {
            printZaporedje(stevila, l, r, stevila[l]);
            return stevila[l];
        }

        int middle = (l + r) / 2;

        // tempSum moramo inicializirati tako kot pise v navodilih,
        // ce damo 0 ne pride prav
        int tempSum = stevila[middle];
        int lSum = tempSum;

        // max sum na levi polovici
        for (int i = middle - 1; i >= l; i--) {
            tempSum += stevila[i];
            if (tempSum > lSum) {
                lSum = tempSum;
            }
        }

        tempSum = stevila[middle + 1];
        int rSum = tempSum;
        // max sum na desni polovici
        for (int i = middle + 2; i <= r; i++) {
            tempSum += stevila[i];
            if (tempSum > rSum) {
                rSum = tempSum;
            }
        }

        // divide, razdelimo problem
        int maxLeftAndRight = Math.max(divide(stevila, l, middle), divide(stevila, middle + 1, r));

        // dobimo max vseh treh
        int midSum = Math.max(maxLeftAndRight, lSum + rSum);
        printZaporedje(stevila, l, r, midSum);
        return midSum;
    }

    public static void solve(int[] stevila) {
        divide(stevila, 0, stevila.length - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> nums = new ArrayList<>();
        while (sc.hasNextInt()) {
            nums.add(sc.nextInt());
        }

        // sem spremenil v array, ker mi je bolj prirocno
        int[] stevila = nums.stream().mapToInt(num -> num).toArray();

        solve(stevila);
        sc.close();
    }
}