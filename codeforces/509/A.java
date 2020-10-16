// Codeforces 509A
import java.util.Scanner;

public class CF509A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int n = SC.nextInt(); // Dimension of table
        int maxVal = getMaxVal(n);
        System.out.println(maxVal);
    }

    // Computes and returns the maximum value in the table
    static int getMaxVal(int n) {
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; ++i) {
            arr[i][0] = arr[0][i] = 1;
        }
        for (int i = 1; i < n; ++i)
            for (int j = 1; j < n; ++j)
                    arr[i][j] += arr[i][j-1] + arr[i-1][j];
        return arr[n-1][n-1];
    } // Alternatively, Find (2n-2)C(n-1), notice this from the printed array, relating it to the Pascal's Triangle
}