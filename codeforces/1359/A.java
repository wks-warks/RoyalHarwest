//Codeforces 1359A 
import java.util.Scanner;

public class CF1359A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int cards = SC.nextInt();
            int jokers = SC.nextInt();
            int players = SC.nextInt();
            int maxPoints = computeMaxPoints(cards, jokers, players);
            System.out.println(maxPoints);
        }
    }

    // Computes and returns maximum possible number of points that can be won by the winner
    static int computeMaxPoints(int cards, int jokers, int players) {
        int jokersWithWinner = Math.min(cards/players, jokers);
        int jokersWithRunnerUp = MathLib.LIG(jokers-jokersWithWinner, players-1);
        return jokersWithWinner - jokersWithRunnerUp;
    }
}

// Contains Standard Math Functions
class MathLib {
    // Computes and returns least integer greater than or equal to (double) numerator/denominator
    static int LIG(int numerator, int denominator) {
        return (numerator+denominator-1)/denominator;
    }
}