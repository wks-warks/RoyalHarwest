//Codeforces 1422A 
import java.util.Scanner;

public class CF1422A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int a = SC.nextInt();
            int b = SC.nextInt();
            int c = SC.nextInt();
            System.out.println(Math.max(Math.max(a, b), c) + 1);
        }
    }
}
