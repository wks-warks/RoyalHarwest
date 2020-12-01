//Codeforces 1213B 
import java.util.Scanner;

public class CF1213B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int days = SC.nextInt();
            int[] prices = new int[days];
            for (int d = 0; d < days; ++d) {
                prices[d] = SC.nextInt();
            }
            int badDays = computeBadDays(prices);
            System.out.println(badDays);
        }
    }

    static int[] bestPrice;

    // Computes and returns number of bad days on the basis of prices
    static int computeBadDays(int[] prices) {
        bestPrice = new int[prices.length];
        bestPrice[bestPrice.length-1] = prices[prices.length-1];
        for (int day = bestPrice.length-2; day >= 0; --day) {
            bestPrice[day] = Math.min(bestPrice[day+1], prices[day]);
        }

        int badDays = 0;
        for (int i = 0; i < prices.length-1; ++i) {
            if (bestPrice[i+1] < prices[i]) {
                badDays += 1;
            }
        }
        return badDays;
    }
}
