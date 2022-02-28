import java.util.Random;

public class Izziv {
    // izmeri povrecni cas iskanje v tabeli dolzine n
    public static long timeLinear(int n) {
        int[] tabela = generateTable(n);
        Random rand = new Random();
        // zacnemo meriti cas
        long startTime = System.nanoTime();

        for (int i = 0; i < 1000; i++) {
            int v = rand.nextInt(n) + 1;
            findLinear(tabela, v);
        }

        long executionTime = System.nanoTime() - startTime;
        return executionTime / 1000;
    }

    public static long timeBinary(int n) {
        int[] tabela = generateTable(n);
        Random rand = new Random();
        // zacnemo meriti cas
        long startTime = System.nanoTime();

        for (int i = 0; i < 1000; i++) {
            int v = rand.nextInt(n) + 1;
            findBinary(tabela, 0, tabela.length - 1, v);
        }

        long executionTime = System.nanoTime() - startTime;
        return executionTime / 1000;
    }

    public static int findBinary(int[] a, int l, int r, int v) {
        if (l > r) {
            return -1;
        }
        int middle = (l + r) / 2;
        int val = a[middle];

        if (v > val) {
            return findBinary(a, middle + 1, r, v);
        } else if (v < val) {
            return findBinary(a, l, middle - 1, v);
        }
        return middle;
    }

    public static int findLinear(int[] a, int v) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == v) {
                return i;
            }
        }
        return -1;
    }

    // generira tabelo celih stevil od 1 do n
    public static int[] generateTable(int n) {
        int[] table = new int[n];
        for (int i = 0; i < table.length; i++) {
            table[i] = i + 1;
        }

        return table;
    }

    public static void main(String[] args) {
        System.out.println("    n    |   linearno   |   dvojisko  ");
        System.out.println("---------+--------------+-------------");

        // ta vrstice so tukaj, da potem pridejo bolj pravilne stevilke pri izpisu bolj
        // pravilne
        timeLinear(50000);
        timeBinary(50000);

        for (int i = 1000; i <= 100000; i += 1000) {
            long timeLin = timeLinear(i);
            long timeBin = timeBinary(i);

            // StdDraw.setPenColor(StdDraw.RED);
            // StdDraw.point(i + 1000, timeLin + 5000);
            // StdDraw.setPenColor(StdDraw.GREEN);
            // StdDraw.point(i + 1000, timeBin + 5000);

            System.out.printf("%8d | %12d | %10d \n", i, timeLin, timeBin);
        }
    }
}