// Codeforces 1409A
import java.util.Scanner;

public class CF1409A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int testCases = SC.nextInt();
        for (int t = 0; t < testCases; ++t) {
            int a = SC.nextInt();
            int b = SC.nextInt();
            int movesToEqualize = computeMovesToEqualize(a, b);
            System.out.println(movesToEqualize);
        }
    }

    // Computes and returns minimum number of moves required to equalize a and b, where one move may add or remove 1-10 from a
    static int computeMovesToEqualize(int a, int b) {
        // Using Concept of Least integer greater than: LIG = (numerator+denominator-1) / denominator
        int moves = (Math.abs(a-b) + 9)/10; // Take 10s until impossibe, then finish in upto 1 moves
        return moves;
    }
}