//Codeforces 1324A 
import java.util.Scanner;

public class CF1324A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int columns = SC.nextInt();
            int[] colHts = new int[columns]; // Columns' heights
            for (int c = 0; c < columns; ++c)
                colHts[c] = SC.nextInt();
            boolean canClear = checkIfClearable(colHts);
            out(canClear);
        }
    }

    // Checks and returns whether or not the tetris field can be cleared
    static boolean checkIfClearable(int[] colHts) {
        int parity = colHts[0] % 2; // Each column must have same parity
        for (int i = 1; i < colHts.length; ++i)
            if(colHts[i] % 2 != parity)
                return false; // Differing parity
        return true; // No mismatch found
    }


    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
