//Codeforces 1400A 
import java.util.Scanner;

public class CF1400A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = SC.nextInt();
            String s = SC.next();
            String similar = getSimilar(s, n);
            System.out.println(similar);
        }
    }

    // Computes and returns string "similar" to substrings of s of size n
    static String getSimilar(String s, int n) {
        StringBuilder similar = new StringBuilder();
        char c = s.charAt(n-1); // This character will be a part of all substrings at some position
            for (int i = 0; i < n; ++i)
                similar.append(c);
        return similar.toString();
    }
}
