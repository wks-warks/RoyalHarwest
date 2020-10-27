//Codeforces 1196A 
import java.util.Scanner;
import java.util.Arrays;

public class CF1196A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int queries = SC.nextInt();
        long[] candyPiles = new long[3];
        for (int q = 0; q < queries; ++q) {
            for(int i = 0; i < 3; ++i)
                candyPiles[i] = SC.nextLong();
            long maxCandiesEach = computeMaxCandiesEach(candyPiles);
            System.out.println(maxCandiesEach);
        }
    }

    // Computes and returns max possible candies that can be allocated to each person
    static long computeMaxCandiesEach(long[] candyPiles) {
        Arrays.sort(candyPiles);
        long alice = candyPiles[0];
        long bob = candyPiles[1];
        long greater = Math.max(alice, bob);
        long lesser = Math.min(alice, bob);

        candyPiles[2] -= greater-lesser; // Equalizing: giving some to lesser
        return greater + candyPiles[2]/2; // Equally dividing the remainder with maybe 1 leftover
    }
}
