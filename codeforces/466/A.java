// Codeforces 466A
import java.util.Scanner;

public class CF466A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int totalRides = SC.nextInt();
        int ridesInSpcl = SC.nextInt();
        int costOfOne = SC.nextInt();
        int costOfSpcl = SC.nextInt();

        int minimmumExpense = computeMinimumExpense(totalRides, ridesInSpcl, costOfOne, costOfSpcl);
        System.out.println(minimmumExpense);
    }

    // Computes and returns the minimum amount to be spent to ride 'totalRides' times
    static int computeMinimumExpense(int totalRides, int ridesInSpcl, int costOfOne, int costOfSpcl) {
        if (costOfOne*ridesInSpcl <= costOfSpcl)
            return costOfOne * totalRides;
        else {
            int spclTickets = totalRides/ridesInSpcl; // Number of special tickets definitely bought in this case
            int spclsCost = spclTickets * costOfSpcl;
            int remainingRides = totalRides % ridesInSpcl;

            // Getting costs of remaining rides as minimum of 1-Covered by spcl ticket, 2-Covered by single-ride tickets
            int remainingCost = Math.min(costOfSpcl, costOfOne * remainingRides);
            return spclsCost + remainingCost;
        }
    }
}