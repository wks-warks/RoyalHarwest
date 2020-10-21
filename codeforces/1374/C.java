// Codeforces 1374C
import java.util.Scanner;

public class CF1374C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = SC.nextInt(); // Length of sequence
            String brackSeq = SC.next();
            int movesRequired = computeMovesToCorrect(brackSeq);
            System.out.println(movesRequired);
        }
    }

    // Computes and returns moves required to correct the sequence
    static int computeMovesToCorrect(String brackSeq) {
        int disturbance = 0; // Keeps track of disturbance
        int maxDisturbance = 0;
        for (int i = 0; i < brackSeq.length(); ++i) {
            if (brackSeq.charAt(i) == '(')
                disturbance -= 1;
            else
                disturbance += 1;
            maxDisturbance = Math.max(maxDisturbance, disturbance);
        }
        return maxDisturbance;
    }
}