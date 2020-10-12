// Codeforces 1399B
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class CF1399B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        Integer tests = SC.nextInt();
        for (Integer t = 0; t < tests; ++t) {
            Integer gifts = SC.nextInt();

            Integer[] candies = new Integer[gifts];
            for (int i = 0; i < gifts; ++i)
                candies[i] = SC.nextInt();
            Integer[] oranges = new Integer[gifts];
            for (int i = 0; i < gifts; ++i)
                oranges[i] = SC.nextInt();
            Long requiredMoves = computeMovesRequired(candies, oranges);
            System.out.println(requiredMoves);
        }
    }

    // Computes and returns number of moves required to get appropriate gifts
    static Long computeMovesRequired(Integer[] candies, Integer[] oranges) {
        Integer desiredCandies = Collections.min(Arrays.asList(candies));
        Integer desiredOranges = Collections.min(Arrays.asList(oranges));
        Long totalMoves = 0L;
        for (Integer i = 0; i < candies.length; ++i) {
            Integer doubleMoves = Math.min(candies[i] - desiredCandies, oranges[i] - desiredOranges); // Moves that consume both oranges and candies
            candies[i] -= doubleMoves;
            oranges[i] -= doubleMoves;
            Integer singleMoves = candies[i]-desiredCandies + oranges[i]-desiredOranges;
            totalMoves += (long)doubleMoves + singleMoves;
        }
        return totalMoves;
    }
}