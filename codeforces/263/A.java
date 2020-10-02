// Codeforces 263A
import java.util.Scanner;

public class CF263A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int[][] matrix = new int[5][5];
        for (int r = 0; r < 5; ++r)
            for (int c = 0; c < 5; ++c)
                matrix[r][c] = SC.nextInt();
        
        int movesToBeauty = computeMovesRequired(matrix);
        System.out.println(movesToBeauty);
    }

    // Computes and returns number of moves required to make the matrix beautiful
    static int computeMovesRequired(int[][] matrix) {
        int[] onePos = new int[2]; // stores row and column of '1'
        for (int r = 0; r < 5; ++r)
            for (int c = 0; c < 5; ++c)
                if (matrix[r][c] == 1) {
                    onePos[0] = r;
                    onePos[1] = c;
                    break;
                }
        int movesRequired = Math.abs(onePos[0] - 2) + Math.abs(onePos[1] - 2);
        // Moves to required row = Math.abs(onePos[0] - 2), similarly for required col
        return movesRequired;
    }
}