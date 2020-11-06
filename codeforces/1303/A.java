//Codeforces 1303A 
import java.util.Scanner;

public class CF1303A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            String binStr = SC.next();
            int zeroesRemoved = computeZeroesRemoved(binStr);
            System.out.println(zeroesRemoved);
        }
    }

    // Computes and returns number of zeroes to be removed
    static int computeZeroesRemoved(String binStr) {
        int i;
        for (i = 0; i < binStr.length(); ++i)
            if (binStr.charAt(i) == '1')
                break;
        // now, i points to the first '1'
        
        int toRemove = 0;
        int zeroes = 0; // Zeroes in consideration for being removed
        while (i < binStr.length()) {
            if (binStr.charAt(i) == '1') {
                toRemove += zeroes;
                zeroes = 0;
            }
            else
                zeroes += 1;
            ++i;
        }
        return toRemove;
    }
}
