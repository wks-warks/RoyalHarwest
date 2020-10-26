//Codeforces 1341A 
import java.util.Scanner;

public class CF1341A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int grains = SC.nextInt();
            int grainWeight = SC.nextInt(); // Standard weight of a grain
            int grainError = SC.nextInt(); // Actual weight of any grain may err from standard weight upto this amount
            int totalWeight = SC.nextInt(); // Base total weight
            int totalError = SC.nextInt(); // Margin of error in total weight
            boolean feasibleCompositionExists = checkFeasibility(grains, grainWeight, grainError, totalWeight, totalError);
            out(feasibleCompositionExists);
        }
    }

    // Checks and returns whether or not a feasible composition is possible
    static boolean checkFeasibility(int grains, int grainWeight, int grainError, int totalWeight, int totalError) {
        if (grains * (grainWeight-grainError) <= totalWeight + totalError && grains * (grainWeight + grainError) >= totalWeight - totalError)
            return true;
        else
            return false;
    }

     // Prints output corresponding to condition
     static void out(boolean condition) {
        if (condition)
            System.out.println("Yes");
        else
            System.out.println("No");
     }
}
