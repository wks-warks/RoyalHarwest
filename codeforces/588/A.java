//Codeforces 588A 
import java.util.Scanner;

public class CF588A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int days = SC.nextInt();
        int[] requirement = new int[days]; // Meat Required FOR given days
        int[] prices = new int[days]; // Prices on given days
        for (int d = 0; d < days; ++d) {
            requirement[d] = SC.nextInt();
            prices[d] = SC.nextInt();
        }
        int minCost = computeMinCost(days, prices, requirement);
        System.out.println(minCost);
    }

    // Computes minimum cost of buying meat
    static int computeMinCost(int days, int[] prices, int[] requirement) {
        int[] optimumPrice = new int[days]; // Optimum cost of buying meat FOR a given day (may buy on any day before it)
        optimumPrice[0] = prices[0];
        for (int d = 1; d < days; ++d)
            optimumPrice[d] = Math.min(optimumPrice[d-1], prices[d]);
        int totalCost = 0;
        for (int d = 0; d < days; ++d)
            totalCost += optimumPrice[d] * requirement[d];
        return totalCost;
    }
}
