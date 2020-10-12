// Codeforces 1374A
import java.util.Scanner;

public class CF1374A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int x = SC.nextInt();
            int y = SC.nextInt();
            int n = SC.nextInt();
            // find largest k such that 0 <= k <= n, k % x = y
            int k = getK(x, y, n);
            System.out.println(k);
        }
    }

    // Computes and returns largest k such that 0 <= k <= n, k % x = y
    static int getK(int x, int y, int n) {
        return n - ((n - y) % x); // Remove from m, that number which remains when we remove y and then as many x as possible
        // Therefore the obtained result = y + as many x as possible
    }
}