//Codeforces 1342B 
import java.util.Scanner;

public class CF1342B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            String s = SC.next();
            String periodicString = getPeriodicString(s);
            solution.append(periodicString + "\n");
        }
        System.out.print(solution);
    }

    // Computes and returns periodic string satisfying given conditions
    static String getPeriodicString(String s) {
        boolean hasZeroes = false;
        boolean hasOnes = false;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '0')
                hasZeroes = true;
            else
                hasOnes = true;
        }
        if (!hasOnes || !hasZeroes)
            return s;
        else {
            StringBuilder sb = new StringBuilder();
            sb.append(s.charAt(0));
            for (int i = 1; i < s.length(); ++i) {
                if (s.charAt(i) == s.charAt(i-1)) {
                    sb.append((char)('0' + '1' - s.charAt(i)));
                }
                sb.append(s.charAt(i)); // This is to be done either way.
            }
            return sb.toString();
        }
    }
}
