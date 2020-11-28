//Codeforces 1333B 
import java.util.Scanner;

public class CF1333B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = SC.nextInt();
            int[] a = new int[arrLen];
            int[] b = new int[arrLen];
            for (int i = 0; i < arrLen; ++i)
                a[i] = SC.nextInt();
            for (int i = 0; i < arrLen; ++i)
                b[i] = SC.nextInt();
            boolean canGet =  checkIfCanGet(a, b);
            out(canGet);
        }
    }

    // Checks and returns whether or not array b can be obtained by operating on array a
    static boolean checkIfCanGet(int[] a, int[] b) {
        boolean foundMinusOne = false;
        boolean foundOne = false;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] < b[i] && !foundOne)
                return false;
            else if (a[i] > b[i] && !foundMinusOne)
                return false;
            
            if (a[i] == -1)
                foundMinusOne = true;
            else if (a[i] == 1)
                foundOne = true;

        }

        return true;
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        System.out.println(condition? "YES" : "NO");
    }
}
