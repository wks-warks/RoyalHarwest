//Codeforces 1345A 
import java.util.Scanner;

public class CF1345A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int rows = SC.nextInt();
            int cols = SC.nextInt();
            boolean canSolve = checkSolvability(rows, cols);
            out(canSolve);
        }
    }

    // Checks and returns whether or not the puzzle is solvable
    static boolean checkSolvability(int rows, int cols) {
        return Math.min(rows, cols) == 1 || rows + cols == 4;
    }

    // Prints output corresponding to condtition
    static void out(boolean condtition) {
        if (condtition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
