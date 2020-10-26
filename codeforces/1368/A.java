//Codeforces 1368A 
import java.util.Scanner;

public class CF1368A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int num1 = SC.nextInt();
            int num2 = SC.nextInt();
            int target = SC.nextInt(); // Exceed this number
            int operations = computeOperationsNeeded(num1, num2, target);
            System.out.println(operations);
        }
    }

    // Computes and returns minimum number of operations required
    static int computeOperationsNeeded(int num1, int num2, int target) {
        int prev = Math.max(num1, num2); // Largest previously
        int operations = 1;
        int curr = num1 + num2; // Current largest
        while (curr <= target) {
            operations += 1;
            curr = curr + prev;
            prev = curr - prev;
        }
        return operations;
    }
}