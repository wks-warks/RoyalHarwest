//Codeforces 1354B 
import java.util.Scanner;

public class CF1354B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            String s = SC.next();
            int substringLength = computeSubstringLength(s);
            solution.append(substringLength + "\n");
        }
        System.out.print(solution.toString());
    }

    // Computes and returns shortest length of substring containing all 3 characters
    static int computeSubstringLength(String s) {
        int substringLength = Integer.MAX_VALUE;
        int j, i = 0;
        while (i < s.length()-1) {
            j = i;
            char c1 = s.charAt(j++);
            char c2 = s.charAt(j++);
            if (c1 == c2) {
                i += 1;
                continue;
            }
            while (j < s.length() && s.charAt(j) == c2)
                j++;

            if (j < s.length() && s.charAt(j) != c1 && s.charAt(j) != c2)
                substringLength = Math.min(j-i+1, substringLength);
            i = j-1;
        }

        if (substringLength == Integer.MAX_VALUE)
            return 0;
        else
            return substringLength;
    }
}
