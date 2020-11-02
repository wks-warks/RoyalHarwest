//Codeforces 1401A 
import java.util.Scanner;

public class CF1401A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int presentEnd = SC.nextInt();
            int desiredDiff = SC.nextInt();
            int stepsRequired = computeSteps(presentEnd, desiredDiff);
            System.out.println(stepsRequired);
        }
    }

    // Computes and returns number of steps required
    static int computeSteps(int presentEnd, int desiredDiff) {
        if(desiredDiff >= presentEnd)
            return desiredDiff - presentEnd;
        else
            return Math.abs(presentEnd % 2 - desiredDiff % 2);
    }
}