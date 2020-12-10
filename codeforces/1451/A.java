//Codeforces 1451A 
import java.util.Scanner;

public class CF1451A {
    static final Scanner SC = new Scanner(System.in);
    
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = SC.nextInt();
            int movesToOne = computeMovesToOne(n);
            System.out.println(movesToOne);
        }
    }

    // Computes and returns number of moves required to reach 1.
    static int computeMovesToOne(int n) {
        if (n < 4)
            return n-1;
        else if (n % 2 == 0)
            return 2;
        else
            return 3;
    }
}
