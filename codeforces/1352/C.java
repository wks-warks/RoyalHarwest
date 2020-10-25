// Codeforces 1352C
import java.util.Scanner;

public class CF1352C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = SC.nextInt();
            int k = SC.nextInt();
            int kThIndivisible = getKthIndivisible(n, k);
            System.out.println(kThIndivisible);
        }
    }

    // Computes and returns k-th number indivisible by n
    static int getKthIndivisible(int n, int k) {
        int period = n-1;
        int coveredPeriods = (k-1) / period;
        int kThIndivisible = coveredPeriods*n + (k%period == 0 ? period : k%period);
        return kThIndivisible;
    }
}