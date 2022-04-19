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
    // poly length bo vedno potenca stevila 2
    public static double[] dft(double[] poly) {
        if (poly.length == 1) {
            return poly;
        }

        double[] evenTerms = new double[poly.length / 2];
        double[] oddTerms = new double[poly.length / 2];
        int index = 0;
        for (int i = 0; i < poly.length; i++) {
            if (i % 2 == 0) {
                evenTerms[index] = poly[i];
            } else {
                oddTerms[index] = poly[i];
                index++;
            }
        }
        // razdelimo na sode in lihe
        System.out.println("Initial poly: " + Arrays.toString(poly));
        System.out.println("Even terms: " + Arrays.toString(evenTerms));
        System.out.println("Odd terms: " + Arrays.toString(oddTerms));
        return null;
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

        double[] firstPoly = new double[lenOfPoly];
        double[] secondPoly = new double[lenOfPoly];

        for (int i = 0; i < numOfArguments; i++) {
            firstPoly[i] = sc.nextDouble();
        }

        for (int i = 0; i < numOfArguments; i++) {
            secondPoly[i] = sc.nextDouble();
        }

        System.out.println("Prvi polinom: " + Arrays.toString(firstPoly));
        System.out.println("Drugi polinom: " + Arrays.toString(secondPoly));
        System.out.println("Len of poly: " + lenOfPoly);

        double[] firstPoly_val = dft(firstPoly);
        // double[] secondPoly_val = dft(secondPoly);

        sc.close();
    }
}
