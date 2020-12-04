//Codeforces 1330A 
import java.util.Scanner;
import java.util.Arrays;

public class CF1330A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int rounds = SC.nextInt();
            int additionalAllowed = SC.nextInt();
            int[] ranks = new int[rounds];
            for (int r = 0; r < rounds; ++r)
                ranks[r] = SC.nextInt();
            
            int maxCoverable = computeMaxCoverable(rounds, additionalAllowed, ranks);
            System.out.println(maxCoverable);
        }
    }

    // Computes and return max rounds coverable
    static int computeMaxCoverable(int rounds, int additionalAllowed, int[] ranks) {
        Arrays.sort(ranks);
        int prev = 0;
        for (int i = 0; i < ranks.length; ++i) {
            int jump = ranks[i] - prev - 1;
            if (jump > additionalAllowed) {
                return prev + additionalAllowed;
            }
            else if (jump > 0) {
                additionalAllowed -= jump;
            }
            prev = ranks[i];
        }

        return prev + additionalAllowed;
    }
}
