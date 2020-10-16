// Codeforces 1371A
import java.util.Scanner;

public class CF1371A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int sticks = SC.nextInt();
            int sameLengthed = getMaxSameLengthed(sticks);
            System.out.println(sameLengthed);
        }
    }

    // Computes and returns the maximum number of sticks of the same length that can be obtained
    static int getMaxSameLengthed(int sticks) {
        return (sticks+1) / 2; // Can be seen that matching n-i, i and getting n gives the maximum number of sticks of the same length
    }
}