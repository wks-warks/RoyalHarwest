// Codeforces 1328A
import java.util.Scanner;

public class CF1328A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int a = SC.nextInt();
            int b = SC.nextInt();
            int movesRequired = computeMovesRequired(a, b);
            System.out.println(movesRequired);
        }
    }

    // Computes minimum number of moves required to make a divisible by b (in one move, a->a+1)
    static int computeMovesRequired(int a, int b) {
        if (a % b == 0)
            return 0;
        else
            return b - a % b;
    }
}