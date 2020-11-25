//Codeforces 1355A 
import java.util.Scanner;

public class CF1355A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            long a1 = SC.nextLong();
            long k = SC.nextLong();
            long ak = computeAk(a1, k);
            System.out.println(ak);
        }
    }

    // Computes and returns ak, given a1 and k
    static long computeAk(long a1, long k) {
        int minDigit = 9;
        int maxDigit = 0;
        long copy = a1;
        while (copy > 0) {
            int digit = (int)(copy % 10);
            copy /= 10;
            minDigit = Math.min(minDigit, digit);
            maxDigit = Math.max(maxDigit, digit);
        }
        if (minDigit == 0 || k == 1)
            return a1;
        else
            return computeAk(a1 + minDigit*maxDigit, k-1);
    }
}
