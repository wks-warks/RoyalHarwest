// Codeforces 1311A
import java.util.Scanner;

public class CF1311A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int given = SC.nextInt(); // Given Number
            int desired = SC.nextInt(); // Number to be obtained
            int movesRequired = computeMovesRequired(given, desired);
            System.out.println(movesRequired);
        }
    }

    // Computes and returns the moves required to obtain the desired number
    static int computeMovesRequired(int given, int desired) {
        if (given > desired) {
            if (((given-desired) & 1) == 0)
                return 1;
            else
                return 2;
        }
        else if (given < desired) {
            if (((desired-given) & 1) == 1)
                return 1;
            else
                return 2;
        }
        else
            return 0;
    }
}