// Codeforces 758A
import java.util.Scanner;

public class CF758A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int citizens = SC.nextInt();
        int[] wealth = new int[citizens];
        for (int i = 0; i < wealth.length; ++i)
            wealth[i] = SC.nextInt();
        int costToEqualize = computeEqualizingCost(wealth);
        System.out.println(costToEqualize);
    }

    // Computes and returns cost to equalize
    static int computeEqualizingCost(int[] wealth) {
        int sum = 0;
        int targetWealth = 0; // Wealthiest person's netWorth
        for (int i = 0; i < wealth.length; ++i) {
            sum += wealth[i];
            targetWealth = Math.max(targetWealth, wealth[i]);
        }
        int targetSum = targetWealth * wealth.length; // targetWealth * number of citizens
        return targetSum - sum;
    }
}