//Codeforces 1295A 
import java.util.Scanner;

public class CF1295A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int maxSegments = SC.nextInt(); // Maximum number of segments that may be turned on
            solution.append(testSolution(maxSegments) + "\n");
        }
        System.out.print(solution);
    }

    // Computes and returns solution for given test case
    static String testSolution(int maxSegments) {
        StringBuilder rightPortion = new StringBuilder();
        while (maxSegments > 3) {
            rightPortion.append('1');
            maxSegments -= 2;
        }
        if ((maxSegments & 1) == 1)
            return "7" + rightPortion.toString();
        else
            return "1" + rightPortion.toString();
    }
}
