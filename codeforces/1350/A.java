//Codeforces 1350A 
import java.util.Scanner;

public class CF1350A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = SC.nextInt();
            int k = SC.nextInt();
            int oracNumber = getOracNumber(n, k); // Number after adding f(n) to it k times
            System.out.println(oracNumber);
        }
    }

    // Computes and returns Orac's desired number
    static int getOracNumber(int n, int k) {
        int smallestFactor = n;
        for (int i = 2; i * i <= n; ++i)
            if (n % i == 0) {
                smallestFactor = i;
                break;
            }
        return n + smallestFactor + 2 * (k-1);
    }
}
