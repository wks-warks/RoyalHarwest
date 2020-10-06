// Codeforces 405A
import java.util.Scanner;
import java.util.Arrays;

public class CF405A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numCols = SC.nextInt(); // Number of columns
        int[] colHt = new int[numCols]; // Heights of the columns
        for (int c = 0; c < numCols; ++c)
            colHt[c] = SC.nextInt();
        // Switching gravity:
        Arrays.sort(colHt);
        out(colHt);
    }

    // Prints the output for the array
    static void out(int[] arr) {
        for (int i = 0; i < arr.length; ++i)
            System.out.print(arr[i] + " ");
    }
}