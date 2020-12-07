//Codeforces 1455A 
import java.util.Scanner;

public class CF1455A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            String n = SC.next();
            int differentGx = countDifferentGx(n);
            System.out.println(differentGx);
        }
    }

    // Computes and returns number of different Gx values obtainable
    static int countDifferentGx(String n) {
        return n.length();        
    }
}
