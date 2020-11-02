//Codeforces 34B 
import java.util.Scanner;
import java.util.Arrays;

public class CF34B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tvSets = SC.nextInt();
        int carryingCapacity = SC.nextInt();
        int[] prices = new int[tvSets]; // TV-prices
        for (int set = 0; set < tvSets; ++set)
            prices[set] = SC.nextInt();
        int maxProfit = computeProfit(prices, carryingCapacity);
        System.out.println(maxProfit);
    }

    // Computes and returns the maximum possible profit Bob can make
    static int computeProfit(int[] prices, int carryingCapacity) {
        Arrays.sort(prices); // We would like to follow a greedy approach and take the ones that give us the most money
        // Considering the ones that cost the least (probably give us money)
        int profit = 0;
        for (int i = 0; i < carryingCapacity; ++i)
            if (prices[i] < 0)
                profit += Math.abs(prices[i]);
            else
                break;
        return profit;
    }
}
