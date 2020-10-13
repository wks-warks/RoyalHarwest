// Codeforces 189A - DP approach
import java.util.Scanner;
import java.util.HashMap;

public class CF189Adp {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int ribbonLen = SC.nextInt();
        int[] pieceSizes = new int[3];
        for (int i = 0; i < 3; ++i)
            pieceSizes[i] = SC.nextInt();
        HashMap<Integer, Integer> solutions = new HashMap<>(); // Solutions for intermediate steps
        int maxPieces = getMaxPieces(ribbonLen, pieceSizes, solutions);
        System.out.println(maxPieces);
    }

    // Computes and returns maximum number of pieces possible    
    static int getMaxPieces(int ribbonLen, int[] pieceSizes, HashMap<Integer, Integer> solutions) {
        // Base cases
        if (solutions.containsKey(ribbonLen))
            return solutions.get(ribbonLen);
        if (ribbonLen < 0)
            return -1; // i.e Impossible
        if (ribbonLen == 0)
            return 0;
        // Key not present so far
        int pieces = -1;
        for (int i = 0; i < 3; ++i) {
            int piecesBefore = getMaxPieces(ribbonLen-pieceSizes[i], pieceSizes, solutions);
            if (piecesBefore != -1)
                pieces = Math.max(pieces, 1+piecesBefore);
        }
        solutions.put(ribbonLen, pieces);
        return pieces;
    }
}