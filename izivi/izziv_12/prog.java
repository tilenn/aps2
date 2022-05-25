import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

class Node {
    int id;
    // vhodno vozlisce -> razdalja
    HashMap<Integer, Integer> node2Value = new HashMap<>();

    public Node(int id) {
        this.id = id;
    }

}

public class prog {
    public static void printValues(Integer[] arr) {
        for (Integer integer : arr) {
            if (integer == null) {
                System.out.print("Inf ");
            } else {
                System.out.printf("%d ", integer);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfNodes = sc.nextInt();

        // inicializirmo node
        Node[] nodes = new Node[numOfNodes];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i);
        }

        Integer[] v1 = new Integer[numOfNodes];
        Integer[] v2 = new Integer[numOfNodes];

        while (sc.hasNextInt()) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int length = sc.nextInt();

            // v koncno vozlisce si shranimo dolzino (length) povezave ter iz katerega
            // vozlisca pride (from)

            nodes[to].node2Value.put(from, length);
        }

        v2[0] = 0;
        System.out.printf("h%d: ", 0);
        printValues(v2);
        v1 = Arrays.copyOf(v2, numOfNodes);
        for (int i = 1; i < numOfNodes; i++) {
            System.out.printf("h%d: ", i);
            for (int j = 0; j < v2.length; j++) {
                HashMap<Integer, Integer> sosedi = nodes[j].node2Value;
                for (Integer key : sosedi.keySet()) {
                    int v = sosedi.get(key);
                    Integer tmp = v1[key];
                    // System.out.println("tmp:" + tmp, " v: " + v);
                    if (tmp == null) {
                        continue;
                    } else {
                        if (v2[j] == null || v + tmp < v2[j]) {
                            v2[j] = v + tmp;
                        }
                    }
                }
            }
            printValues(v2);
            v1 = Arrays.copyOf(v2, numOfNodes);
            // break;
        }

        sc.close();
    }
}