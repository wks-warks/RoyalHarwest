//Codeforces 431C 
import java.util.Scanner;

public class CF431C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int n = SC.nextInt();
        int k = SC.nextInt();
        int d = SC.nextInt();
        int ways = computeWays(n, k, d);
        System.out.println(ways);
    }

    static int[] dpAtLeastD;
    static int[] dp;
    // Computes and returns the answer
    static int computeWays(int n, int k , int d) {
        dp = new int[n+1];
        dp[0] = 1;
        dpAtLeastD = new int[n+1];
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j <= Math.min(k, n-i); ++j) {
                dp[i+j] += dp[i];
                if (j >= d)
                    dpAtLeastD[i+j] += dp[i];
                else
                    dpAtLeastD[i+j] += dpAtLeastD[i];
                dp[i+j] %= 1_000_000_007;
                dpAtLeastD[i+j] %= 1_000_000_007;
            }
        }
        return dpAtLeastD[n];
    }
}
