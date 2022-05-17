import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

class Par implements Comparable<Par> {
    int volumen;
    int cena;
    int dirty;

    public Par(int volumen, int cena) {
        this.volumen = volumen;
        this.cena = cena;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", this.volumen, this.cena);
    }

    @Override
    public int compareTo(Par p) {
        return this.cena - p.cena;
    }
}

public class Izziv {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int volumen = sc.nextInt();
        int st_predmetov = sc.nextInt();

        ArrayList<Par> pari = new ArrayList<>();
        for (int i = 0; i < st_predmetov; i++) {
            int tmp_volumen = sc.nextInt();
            int tmp_cena = sc.nextInt();
            pari.add(new Par(tmp_volumen, tmp_cena));
        }

        ArrayList<Par> validPari = new ArrayList<>();
        validPari.add(new Par(0, 0));
        System.out.println("0: " + validPari.get(0).toString());

        for (int i = 0; i < st_predmetov; i++) {
            int tmp_volumen = pari.get(i).volumen;
            int tmp_cena = pari.get(i).cena;

            int st_valid_parov = validPari.size();

            // za vsak par dodamo nov par
            // System.out.print("Dodamo: ");
            for (int j = 0; j < st_valid_parov; j++) {
                Par tmp = validPari.get(j);
                Par zaDodati = new Par(tmp.volumen + tmp_volumen, tmp.cena + tmp_cena);
                // System.out.print(zaDodati.toString() + " ");
                validPari.add(zaDodati);
            }
            // System.out.println();

            // sortiramo
            validPari.sort(null);
            // izbrisemo neperspektivne

            for (int j = 0; j < validPari.size(); j++) {
                Par ena = validPari.get(j);
                if (ena.volumen > volumen) {
                    ena.dirty = 1;
                }
                for (int k = j + 1; k < validPari.size(); k++) {
                    Par dva = validPari.get(k);

                    if ((ena.volumen <= dva.volumen && ena.cena >= dva.cena) || dva.volumen > volumen) {
                        dva.dirty = 1;
                    } else if ((ena.volumen >= dva.volumen && ena.cena <= dva.cena) || ena.volumen > volumen) {
                        ena.dirty = 1;
                    }
                }
            }

            Iterator itr = validPari.iterator();
            // System.out.print("Remove: ");
            while (itr.hasNext()) {
                Par temp = (Par) itr.next();
                if (temp.dirty == 1) {
                    // System.out.print(temp.toString() + " ");
                    itr.remove();
                }
            }
            // System.out.println();

            System.out.printf("%d: ", i + 1);
            for (Par par : validPari) {
                System.out.print(par.toString() + " ");
            }
            System.out.println();

        }

        sc.close();
    }
}