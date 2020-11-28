//Codeforces 1395A 
import java.util.Scanner;

public class CF1395A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int r = SC.nextInt();
            int g = SC.nextInt();
            int b = SC.nextInt();
            int w = SC.nextInt();
            boolean palindromic = checkIfPalindromic(r, g, b, w);
            out(palindromic);
        }
    }

    // Checks and returns whether or not the balls can be arranged in a palindromic order post a finite number of operations (possibly 0)
    static boolean checkIfPalindromic(int r, int g, int b, int w) {
        int even = 0;
        if ((r & 1) == 0)
            even += 1;
        if ((g & 1) == 0)
            even += 1;
        if ((b & 1) == 0)
            even += 1;
        if ((w & 1) == 0)
            even += 1;
        
        if (even > 2 || even == 0)
            return true;
        if (even == 1 && r > 0 && g > 0 && b > 0)
            return true;
        else
            return false;
    }


    // Prints output corresponding to condition
    static void out(boolean condition) {
        System.out.println(condition ? "Yes" : "No");
    }
}
