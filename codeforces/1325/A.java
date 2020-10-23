// Codeforces 1325A
import java.util.Scanner;

public class CF1325A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int x = SC.nextInt();
            int a = 1, b = x-1; // Solution
            System.out.println(a + " " + b);
        }
    }
}