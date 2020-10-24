// Codeforces 476A
import java.util.Scanner;

public class CF476A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int stairs = SC.nextInt();
        int multipleOf = SC.nextInt(); // Moves need to be a multiple  of this
        int movesToTop = computeMoves(stairs, multipleOf);
        System.out.println(movesToTop);
    }

    // Computes and returns the minimum number of moves required to move to top following the given conditions
    static int computeMoves(int stairs, int multipleOf) {
        if (stairs < multipleOf)
            return -1;

        int moves = LIG(stairs, 2*multipleOf);
        return multipleOf * moves;
    }

    // Computes and returns Least Integer Greater than (double) numerator/denominator
    static int LIG(int numerator, int denominator) {
        return (numerator + denominator-1) / denominator;
    }
}