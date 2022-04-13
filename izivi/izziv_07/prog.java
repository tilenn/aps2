import java.util.ArrayList;
import java.util.Scanner;

public class prog {
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimitiveRoot(int candidate, int power, int n) {
        for (int i = 1; i < power; i++) {
            if (powZaSirotinju(candidate, i, n) == 1) {
                return false;
            }
            // powZaSirotinju(candidate, i, n) == 1
        }
        return true;
    }

    public static ArrayList<Integer> getPrimitiveRoots(int n, int power) {
        ArrayList<Integer> primitiveRoots = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (powZaSirotinju(i, power, n) == 1 && isPrimitiveRoot(i, power, n)) {
                primitiveRoots.add(i);
            }
            // powZaSirotinju(i, power, n) == 1
        }
        return primitiveRoots;
    }

    public static long powZaSirotinju(long num, int power, int mod) {
        long tmp = num;
        for (int i = 1; i < power; i++) {
            num = num * tmp;
            num = num % mod;
        }
        return num % mod;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int c = n + 1;
        ArrayList<Integer> result = new ArrayList<>();
        while (true) {
            if (isPrime(c)) {
                result = getPrimitiveRoots(c, n);
                // System.out.println("Prime number: " + c + ", size: " + result.size());
                if (result.size() != 0) {
                    break;
                }
            }
            c++;
        }

        System.out.print(c + ": ");
        int resultSize = result.size();
        for (int i = 0; i < resultSize; i++) {
            System.out.print(result.get(i) + " ");
        }
        System.out.println();

        long[][] vandermonMatrix = new long[n][n];
        int firstPrimitiveRoot = result.get(0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    vandermonMatrix[i][j] = 1;
                } else {
                    // vandermonMatrix[i][j] = (long) Math.pow(firstPrimitiveRoot, i * j) % c;
                    vandermonMatrix[i][j] = (long) powZaSirotinju(firstPrimitiveRoot, i * j, c);
                }
                System.out.printf("%2d ", vandermonMatrix[i][j]);
            }
            System.out.println();
        }

        sc.close();
    }
}
