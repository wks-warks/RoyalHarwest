// Codeforces 1327A
import java.util.Scanner;

public class CF1327A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = SC.nextInt();
            int k = SC.nextInt();
            boolean representable = checkRepresentability(n, k);
            out(representable);
        }
    }

    // Checks and returns whether or not n can be represented as a sum of k distinct positive odd integers
    static boolean checkRepresentability(int n, int k) {
        long kSum = oddSum(k);
        if (n%2 != k%2 || kSum > n)
            return false;
        else
            return true;
    }

    // Returns sum of first odd numbers upto 'terms'
    static long oddSum(int terms) {
        return (long) terms*terms;
    }

    // Prints output corresponding to the condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}