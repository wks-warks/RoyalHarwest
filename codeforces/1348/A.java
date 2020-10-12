// Codeforces 1348A
import java.util.Scanner;

public class CF1348A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int coins = SC.nextInt();
            // values: 2^1, 2^2, 2^3 ... 2^numberOfCoins where 2^x = exp(2, x)
            long minDifference = getMinDifference(coins); // Minimum difference between values of two groups of equal size from the coins
            System.out.println(minDifference);
        }
    }

    // Computes and returns Minimum Possible Difference between the values of the two groups
    static long getMinDifference(int coins) {
        long totalSum = sumTerms(coins);
        int groupSize = coins / 2; // Size of group in terms of number of coins
        long sumOfGroup1 = (1<<coins) + sumTerms(groupSize-1); // largest valued coin is paired with all the smallest ones
        long sumOfGroup2 = totalSum - sumOfGroup1;
        return sumOfGroup1 - sumOfGroup2;
    }

    // Computes and returns sum of the series 2^1 + 2^2 + ... + 2^pow
    static long sumTerms(int pow) {
        return (1<<(pow+1)) - 2;
    } 
}