// Codeforces 337A
import java.util.Scanner;
import java.util.Arrays;

public class CF337A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numStudents = SC.nextInt();
        int numPuzzles = SC.nextInt();
        int[] puzzleSizes = new int[numPuzzles];
        for (int p = 0; p < numPuzzles; ++p)
            puzzleSizes[p] = SC.nextInt();
        int minDiff = getMinDiff(puzzleSizes, numStudents);
        System.out.println(minDiff);
    }

    // Gets minimum difference between lrg and sm puzzle chosen (out of n puzzles taken)
    static int getMinDiff(int[] puzzleSizes, int numStudents) {
        Arrays.sort(puzzleSizes); // We take a contiguous subsegment of this array, to minimize difference
        int minDiff = Integer.MAX_VALUE;
        int sm = 0; // Size of smallest puzzle taken
        int lrg = numStudents - 1; // Size of largest puzzle taken
        while (lrg < puzzleSizes.length) {
            int diff = puzzleSizes[lrg] - puzzleSizes[sm];
            minDiff = Math.min(minDiff, diff);
            
            // Considering the next subsegment
            ++sm;
            ++lrg;
        }
        return minDiff;
    }
}