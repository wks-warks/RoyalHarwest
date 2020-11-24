//Codeforces 1365A 
import java.util.Scanner;

public class CF1365A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int rows = SC.nextInt();
            int cols = SC.nextInt();
            int[][] matrix = new int[rows][cols];
            for (int r = 0; r < rows; ++r) {
                for (int c = 0; c < cols; ++c)
                    matrix[r][c] = SC.nextInt();
            }
            int movesPossible = computeMovesPossible(matrix);
            String winner = movesPossible % 2 == 0 ? "Vivek" : "Ashish";
            System.out.println(winner);
        }
    }

    // Computes and returns number of moves possible from the given state of the game
    static int computeMovesPossible(int[][] matrix) {
        boolean[] rowClaimed = new boolean[matrix.length];
        int rowClaimCount = 0;
        boolean[] colClaimed = new boolean[matrix[0].length];
        int colClaimCount = 0;

        for (int r = 0; r < matrix.length; ++r) {
            for (int c = 0; c < matrix[r].length; ++c) {
                boolean claimed = matrix[r][c] == 1;
                if (claimed) {
                    if (!rowClaimed[r]) {
                        rowClaimed[r] = true;
                        rowClaimCount += 1;
                    }
                    if (!colClaimed[c]) {
                        colClaimed[c] = true;
                        colClaimCount += 1;
                    }
                }
            }
        }

        int rowsUnclaimed = rowClaimed.length - rowClaimCount;
        int colsUnclaimed = colClaimed.length - colClaimCount;
        return Math.min(rowsUnclaimed, colsUnclaimed);
    }
}
