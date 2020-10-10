// Codeforces 189A
import java.util.Scanner;
import java.util.Arrays;

public class CF189A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int ribbonLen = SC.nextInt(); // Length of ribbon
        int[] pieceSizes = new int[3]; // Allowed piece sizes
        for (int i = 0; i < 3; ++i)
            pieceSizes[i] = SC.nextInt();
        int maxPieces = computeMaxPieces(ribbonLen, pieceSizes);
        System.out.println(maxPieces);
    }

    // Computes and returns maximum number of pieces that the ribbon can be cut into
    static int computeMaxPieces(int ribbonLen, int[] pieceSizes) {
        Arrays.sort(pieceSizes);
        int maxPieces = 0;
        int typeThreeMax = ribbonLen / pieceSizes[2];        
        for (int i = 0; i <= typeThreeMax; ++i) {
            int takenLen = i * pieceSizes[2];
            for (int j = 0; j * pieceSizes[1] <= ribbonLen - takenLen; ++j) {
                takenLen += j * pieceSizes[1];
                int k = (ribbonLen - takenLen) / pieceSizes[0];
                takenLen += k * pieceSizes[0];
                if (ribbonLen == takenLen)
                    maxPieces = Math.max(maxPieces, i+j+k); // i-pieces of size1, j of size-2, k of size-3
                takenLen = i * pieceSizes[2]; // So that changes from a prior loop don't follow onto the next loop
                // Alternatively, takenLen could've been kept local to the innermost loop but we do it this way as we'd like to keep this potential bug in check
            }
        }
        return maxPieces;
    }
}