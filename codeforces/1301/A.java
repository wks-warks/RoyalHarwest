//Codeforces 1301A 
import java.util.Scanner;

public class CF1301A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            String a = SC.next();
            String b = SC.next();
            String c = SC.next();
            boolean canEqualize = checkIfEqualizable(a, b, c);
            System.out.println(canEqualize? "YES" : "NO");
        }
    }

    // Checks and returns whether the strings can be Equalized
    static boolean checkIfEqualizable(String a, String b, String c) {
        for (int i = 0; i < c.length(); ++i) {
            if (c.charAt(i) != a.charAt(i) && c.charAt(i) != b.charAt(i))
                return false;
        }
        return true;
    }
}
