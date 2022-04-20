import java.util.Arrays;
import java.util.Scanner;

class Complex {
    double re;
    double im;

    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    public String toString() {
        double tRe = (double) Math.round(re * 100000) / 100000;
        double tIm = (double) Math.round(im * 100000) / 100000;
        if (tIm == 0)
            return tRe + "";
        if (tRe == 0)
            return tIm + "i";
        if (tIm < 0)
            return tRe + "-" + (-tIm) + "i";
        return tRe + "+" + tIm + "i";
    }

    public Complex conj() {
        return new Complex(re, -im);
    }

    // sestevanje
    public Complex plus(Complex b) {
        Complex a = this;
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }

    // odstevanje
    public Complex minus(Complex b) {
        Complex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real, imag);
    }

    // mnozenje z drugim kompleksnim stevilo
    public Complex times(Complex b) {
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag);
    }

    // mnozenje z realnim stevilom
    public Complex times(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    // reciprocna vrednost kompleksnega stevila
    public Complex reciprocal() {
        double scale = re * re + im * im;
        return new Complex(re / scale, -im / scale);
    }

    // deljenje
    public Complex divides(Complex b) {
        Complex a = this;
        return a.times(b.reciprocal());
    }

    // e^this
    public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    // potenca komplesnega stevila
    public Complex pow(int k) {

        Complex c = new Complex(1, 0);
        for (int i = 0; i < k; i++) {
            c = c.times(this);
        }
        return c;
    }

}

public class Prog {
    public static void printComplexArray(Complex[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i].toString());
            if (i != arr.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    // TODO: ne double, temvec COMPLEX
    // poly length bo vedno potenca stevila 2
    // v == -1 inverzni
    public static Complex[] dft(Complex[] poly, int v) {
        if (poly.length == 1) {
            return poly;
        }

        // razdelimo na sode in lihe
        Complex[] evenTerms = new Complex[poly.length / 2];
        Complex[] oddTerms = new Complex[poly.length / 2];
        int index = 0;
        for (int i = 0; i < poly.length; i++) {
            if (i % 2 == 0) {
                evenTerms[index] = poly[i];
            } else {
                oddTerms[index] = poly[i];
                index++;
            }
        }

        // System.out.println("Initial poly: " + Arrays.toString(poly));
        // System.out.println("Even terms: " + Arrays.toString(evenTerms));
        // System.out.println("Odd terms: " + Arrays.toString(oddTerms));

        // klicemo rekurzivno
        Complex[] first = dft(evenTerms, v);
        Complex[] second = dft(oddTerms, v);

        // System.out.println("First: " + Arrays.toString(first));
        // System.out.println("Second: " + Arrays.toString(second));

        Complex w = new Complex(0, 2 * Math.PI / poly.length);
        w = w.exp();
        if (v == -1) {
            w = w.conj();
        }
        Complex wk = new Complex(1, 0);

        Complex[] vrednostna = new Complex[poly.length];
        for (int i = 0; i < poly.length / 2; i++) {
            vrednostna[i] = first[i].plus(wk.times(second[i]));
            vrednostna[i + poly.length / 2] = first[i].minus(wk.times(second[i]));
            wk = wk.times(w);
        }
        // System.out.println(Arrays.toString(vrednostna));
        printComplexArray(vrednostna);
        return vrednostna;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // koeficienti polinoma so shranjeni kot array-i doublov
        // ce lenOfPoly ni potenca stevila 2 potem nastavimo na potenco stevila 2
        int lenOfPoly = sc.nextInt();
        int numOfArguments = lenOfPoly;
        if ((Math.log(lenOfPoly) / Math.log(2)) % 1 != 0.0) {
            lenOfPoly = (int) Math.pow(2, Math.ceil(Math.log(lenOfPoly) / Math.log(2)));
        }
        lenOfPoly = lenOfPoly * 2;

        Complex[] firstPoly = new Complex[lenOfPoly];
        Complex[] secondPoly = new Complex[lenOfPoly];

        for (int i = 0; i < lenOfPoly; i++) {
            if (i < numOfArguments) {
                firstPoly[i] = new Complex(sc.nextDouble(), 0);
            } else {
                firstPoly[i] = new Complex(0, 0);
            }
        }

        for (int i = 0; i < lenOfPoly; i++) {
            if (i < numOfArguments) {
                secondPoly[i] = new Complex(sc.nextDouble(), 0);
            } else {
                secondPoly[i] = new Complex(0, 0);
            }
        }

        // System.out.println("Len of poly: " + lenOfPoly);

        Complex[] firstPoly_val = dft(firstPoly, 1);
        Complex[] secondPoly_val = dft(secondPoly, 1);

        // System.out.println("p: " + Arrays.toString(firstPoly_val));
        // System.out.println("d: " + Arrays.toString(secondPoly_val));

        // firstPoly = dft(firstPoly_val, -1);
        // secondPoly = dft(secondPoly_val, -1);
        Complex[] res = new Complex[firstPoly.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = firstPoly_val[i].times(secondPoly_val[i]);
        }

        res = dft(res, -1);
        for (int i = 0; i < res.length; i++) {
            res[i] = res[i].divides(new Complex(lenOfPoly, 0));
        }
        // System.out.println(Arrays.toString(res));
        printComplexArray(res);

        sc.close();
    }
}
