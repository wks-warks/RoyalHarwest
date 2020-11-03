//Codeforces 1077A 
import java.util.Scanner;

public class CF1077A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int oddJump = SC.nextInt(); // Jumps right
            int evenJump = SC.nextInt(); // Jumps left
            int jumps = SC.nextInt();
            long finalPos = computeFinalPos(oddJump, evenJump, jumps);
            System.out.println(finalPos);
        }
    }

    // Computes and returns final Position of frog
    static long computeFinalPos(int oddJump, int evenJump, int jumps) {
        if (jumps % 2 == 0)
            return (long) oddJump * jumps/2 - (long) evenJump * jumps / 2;
        else
            return oddJump + computeFinalPos(oddJump, evenJump, jumps - 1);
    }
}
