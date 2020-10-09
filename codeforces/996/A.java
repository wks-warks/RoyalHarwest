// Codeforces 996A
import java.util.Scanner;

public class CF996A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int cash = SC.nextInt();
        int bills = computeBillsRequired(cash);
        System.out.println(bills);
    }

    // Computes and returns minimum number of bills required to represent given amount of cash
    static int computeBillsRequired(int cash) {
        int toConvert = cash;
        int bills = 0;
        int[] denominations = {100, 20, 10, 5, 1}; // Sorted in descending order
        for (int d = 0; d < denominations.length; ++d) {
            bills += toConvert / denominations[d];
            toConvert %= denominations[d];
        }
        return bills;
    }
}