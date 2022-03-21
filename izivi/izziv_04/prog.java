import java.util.HashMap;
import java.util.Scanner;

public class prog {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] stevila = new int[n];
        int[] binStrings = new int[32];

        for (int i = 0; i < stevila.length; i++) {
            int readNum = sc.nextInt();
            stevila[i] = readNum;
            int numOfOnes = (int) Integer.bitCount(readNum);
            binStrings[numOfOnes] += 1;
        }

        for (int i = 1; i < binStrings.length; i++) {
            binStrings[i] += binStrings[i - 1];
        }

        int[] sortedArr = new int[n];
        for (int i = stevila.length - 1; i >= 0; i--) {
            int numToInsert = stevila[i];
            int numOfOnes = Integer.bitCount(numToInsert);
            int position = binStrings[numOfOnes] - 1;
            System.out.printf("(%d,%d)\n", numToInsert, position);
            sortedArr[position] = stevila[i];
            binStrings[numOfOnes] = position;
        }

        for (int i = 0; i < sortedArr.length; i++) {
            System.out.print(sortedArr[i] + (i == sortedArr.length - 1 ? "" : " "));
        }

        sc.close();
    }
}