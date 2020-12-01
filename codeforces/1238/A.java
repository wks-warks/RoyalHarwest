//Codeforces 1238A 
import java.util.Scanner;

public class CF1238A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            long x = SC.nextLong();
            long y = SC.nextLong();
            System.out.println((x-y != 1) ? "YES" : "NO");
        }
    }
}
