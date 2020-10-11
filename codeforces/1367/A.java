// Codeforces 1367A
import java.util.Scanner;

public class CF1367A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt(); // Number of testCases
        for (int t = 0; t < tests; t++) {
            String b = SC.next();
            String a = getAfrom(b);
            System.out.println(a);
        }
    }

    // Computes and returns string a based off b
    static String getAfrom(String b) {
        StringBuilder a = new StringBuilder();
        a.append(b.charAt(0));
        for (int i = 1; i < b.length(); i += 2)
            a.append(b.charAt(i));
        return a.toString();
    }
}