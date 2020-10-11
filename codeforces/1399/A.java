// Codeforces 1399A
import java.util.Scanner;
import java.util.Arrays;

public class CF1399A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt(); // Number of testCases
        for (int t = 0; t < tests; ++t) {
            int nums = SC.nextInt();
            int[] numsArr = new int[nums];
            for (int i = 0; i < nums; ++i)
                numsArr[i] = SC.nextInt();
            boolean possibleSingleton = checkSingletonPossibility(numsArr);
            out(possibleSingleton);
        }
    }

    // Checks and returns boolean whether or not a singleton set is possibly obtainable
    static boolean checkSingletonPossibility(int[] numsArr) {
        Arrays.sort(numsArr);
        int present = numsArr[0];
        for (int i = 0; i < numsArr.length; ++i) {
            int prev = present;
            present = numsArr[i];
            if (present - prev > 1)
                return false;
        }
        return true; // Some order for removal exists through which a singleton set may be attained by following the rules
    }

    // Print output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}