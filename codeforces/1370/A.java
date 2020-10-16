// Codeforces 1370A
import java.util.Scanner;

public class CF1370A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = SC.nextInt();
            int maxGCD = n/2; // GCD(n, n/2) if n is even or GCD(n-1, (n-1)/2) if n is odd
            System.out.println(maxGCD);
        }
    }
}