//Codeforces 1213A 
import java.util.Scanner;

public class CF1213A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numChips = SC.nextInt();
        int[] chips = new int[numChips];
        for (int c = 0; c < numChips; ++c)
            chips[c] = SC.nextInt();
        int minCost = computeMinCost(chips);
        System.out.println(minCost);
    }

    // Computes and returns minimum cost of getting all pieces on one point
    static int computeMinCost(int[] chips) {
        // first, get all points on either 1 or 2 (you can choose any adjacent numbers), this costs 0 (get odd numbers to 1, even to 2)
        // now, the minimum cost to get all numbers on the same point is Math.min(odd, even)
        int odd = 0;
        int even = 0;
        for (int c = 0; c < chips.length; ++c)
            if ((chips[c] & 1) == 0)
                odd += 1;
            else
                even += 1;
        return Math.min(odd, even);
    }

}
