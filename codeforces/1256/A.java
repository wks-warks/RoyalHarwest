//Codeforces 1256A 
import java.util.Scanner;

public class CF1256A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int nValuedCoins = SC.nextInt();
            int oneValuedCoins = SC.nextInt();
            int n = SC.nextInt();
            int desiredValue = SC.nextInt();
            boolean sufficientChange = checkChangeSufficiency(nValuedCoins, n, oneValuedCoins, desiredValue);
            out(sufficientChange);
        }
    }

    // Checks and returns whether the exact desired amount can be obtained
    static boolean checkChangeSufficiency(int nValuedCoins, int n, int oneValuedCoins, int desiredValue) {
        return ((long)nValuedCoins * n + oneValuedCoins >= desiredValue) && (desiredValue % n <= oneValuedCoins);
    }

    // Prints outpur corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("yes");
        else
            System.out.println("no");
    }

}
