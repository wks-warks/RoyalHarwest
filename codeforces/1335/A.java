// Codeforces 1335A
import java.util.Scanner;

public class CF1335A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int candies = SC.nextInt(); // Number of candies;
            int waysToDivide = (candies-1)/2; // Number of distributions where both have non zero number of candies and Alice has more
            System.out.println(waysToDivide);
        }
    }
}