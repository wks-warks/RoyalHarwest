// Codeforces 4A
import java.util.Scanner;

public class CF4A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int weight = SC.nextInt();
        boolean possibleDistribution = isDistributionPossible(weight);
        printOutput(possibleDistribution);
    }

    // Checks whether or not an acceptable distribution is possible
    static boolean isDistributionPossible(int weight) {
        boolean even = (weight & 1) == 0;
        if (even) {
            if (weight < 4)
                return false; // if weight < 4, it cannot be distributed into TWO even parts
            else
                return true;
        }
        else
            return false;
    }

    // prints output to screen based on whether the conditions for an acceptable distribution are met
    static void printOutput(boolean conditionMet) {
        if (conditionMet)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}