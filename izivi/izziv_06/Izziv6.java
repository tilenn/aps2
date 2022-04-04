import java.util.Scanner;

class Matrix {

    private int[][] m;

    public int n; // only square matrices

    public Matrix(int n) {

        this.n = n;

        m = new int[n][n];

    }

    // set value at i,j
    public void setV(int i, int j, int val) {

        m[i][j] = val;

    }

    // get value at index i,j
    public int v(int i, int j) {

        return m[i][j];

    }

    // return a square submatrix from this
    public Matrix getSubmatrix(int startRow, int startCol, int dim) {

        Matrix subM = new Matrix(dim);

        for (int i = 0; i < dim; i++)

            for (int j = 0; j < dim; j++)

                subM.setV(i, j, m[startRow + i][startCol + j]);

        return subM;

    }

    // write this matrix as a submatrix from b (useful for the result of
    // multiplication)
    public void putSubmatrix(int startRow, int startCol, Matrix b) {

        for (int i = 0; i < b.n; i++)

            for (int j = 0; j < b.n; j++)

                setV(startRow + i, startCol + j, b.v(i, j));

    }

    // matrix addition
    public Matrix sum(Matrix b) {

        Matrix c = new Matrix(n);

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                c.setV(i, j, m[i][j] + b.v(i, j));

            }

        }

        return c;

    }

    // matrix subtraction
    public Matrix sub(Matrix b) {

        Matrix c = new Matrix(n);

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                c.setV(i, j, m[i][j] - b.v(i, j));

            }

        }

        return c;

    }

    public void fillMatrix(Scanner in) {
        int n = this.n;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.setV(i, j, in.nextInt());
            }
        }
    }

    // simple multiplication
    public Matrix mult(Matrix b) {
        Matrix res = new Matrix(this.n);
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                for (int k = 0; k < this.n; k++) {
                    res.setV(i, j, res.v(i, j) + (this.v(i, k) * b.v(k, j)));
                }
            }
        }
        return res;
    }

    // Strassen multiplication
    public Matrix multStrassen(Matrix b, int cutoff) {
        // TODO
        return null;
    }

    public void printMatrix() {
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                System.out.print(this.v(i, j) + " ");
            }
            System.out.println();
        }
    }
}

public class Izziv6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int stopVal = sc.nextInt();

        Matrix a = new Matrix(n);
        a.fillMatrix(sc);
        Matrix b = new Matrix(n);
        b.fillMatrix(sc);

        sc.close();
    }

}
