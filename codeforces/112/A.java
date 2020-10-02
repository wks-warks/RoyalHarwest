// Codeforces 112A
import java.util.Scanner;

public class CF112A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String first = SC.next();
        String second = SC.next();
        int output = computeOutput(first, second);
        System.out.println(output);
    }

    // Computes and returns output
    static int computeOutput(String first, String second) {
        int result = first.compareToIgnoreCase(second);
        // compareToIgnoreCase method returns difference between first case-distinct pair of characters
        // adjusting result to acceptable values:
        if (result < 0)
            return -1;
        else if (result == 0)
            return 0;
        else
            return 1;
    }
}