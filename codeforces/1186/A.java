// Codeforces 1186A
import java.util.Scanner;

public class CF1186A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int participants = SC.nextInt();
        int pens = SC.nextInt();
        int notebooks = SC.nextInt();
        boolean distributionPossible = isDistributionPossible(participants, pens, notebooks);
        out(distributionPossible);
    }

    // Checks and returns whether a satisfactory distribution is possible
    static boolean isDistributionPossible(int participants, int pens, int notebooks) {
        return (pens >= participants) && (notebooks >= participants); // (Every participant can be given at least 1 notebook) AND (Every participant can be given at least 1 pen)
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}