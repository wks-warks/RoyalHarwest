// Codeforces 1360B
import java.util.Scanner;
import java.util.Arrays;

public class CF1360B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int athletes = SC.nextInt(); // Number of athletes
            int[] strengths = new int[athletes];
            for (int a = 0; a < athletes; ++a)
                strengths[a] = SC.nextInt();
            int minDifference = computeMinimumDifference(strengths); // Mininum difference in strength of weakest member of one team and strongest of another
            System.out.println(minDifference);    
        }
    }

    // Computes and returns the minimum difference between the strength of the weakest member of one team and the strongest of the other in the distribution which gives the least difference
    static int computeMinimumDifference(int[] strengths) {
        /*
        * The optimum solution is to find the difference between consecutive elements and return the least of the same
        * In the mentioned case, all elements smaller than the smaller of the two allow for it to be the strongest of its group
        * Similarly, all elements larger than the larger of the two numbers considered would allow for it to be the smallest of its group
        * Other than the two numbers considered, for any solution - no other number contributes to the solution
        */
        Arrays.sort(strengths);
        int minDifference = Integer.MAX_VALUE;
        for (int i = 1; i < strengths.length; ++i)
            minDifference = Math.min(minDifference, strengths[i] - strengths[i-1]); // Since we take Larger-Smaller, it will always be non negative
        return minDifference;
    }
}