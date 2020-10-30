//Codeforces 1391A 
import java.util.Scanner;

public class CF1391A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = SC.nextInt();
            printUpto(n);
        }
    }
    
    // Prints all integers upto n
    static void printUpto(int n) {
        for (int i = 1; i <= n; ++i)
            System.out.print(i + " ");
        System.out.println();
    }
}
