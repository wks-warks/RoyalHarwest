//Codeforces 1454A 
import java.util.Scanner;

public class CF1454A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = SC.nextInt();
            System.out.print(n + " ");
            for (int i = 1; i < n; ++i)
                System.out.print(i + " ");
            System.out.println();
        }
    }
}
