// Codeforces 160A
import java.util.Scanner;
import java.util.stream.*;
import java.util.Arrays;
import java.util.Collections;

public class CF160A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numCoins = SC.nextInt(); // Number of coins
        int[] coins = new int[numCoins]; // Array of coins' values
        for (int c = 0; c < numCoins; ++c)
            coins[c] = SC.nextInt();
        int coinsTaken = computeNumberOfCoinsTaken(coins);
        System.out.println(coinsTaken);
    }

    // Computes minimum number of coins to be taken
    static int computeNumberOfCoinsTaken(int[] coins) {
        int sum = IntStream.of(coins).sum();
        Arrays.sort(coins);
        int accumulatedSum = 0; // Accumulated Sum
        int take = 0; // Coins to be taken
        for (take = 1; take <= coins.length; ++take) {
            accumulatedSum += coins[coins.length - take]; // Picking the largest coins first
            if (2 * accumulatedSum > sum)
                break;
        }
        return take;
    }
}