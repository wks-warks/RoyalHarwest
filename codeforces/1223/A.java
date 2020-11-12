//Codeforces 1223A 
import java.util.Scanner;

public class CF1223A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int queries = SC.nextInt();
        for (int q = 0; q < queries; ++q) {
            int matches = SC.nextInt();
            int additionalRequired = computeAdditional(matches);
            System.out.println(additionalRequired);
        }
    }

    // Computes and returns the additional amount of matches required
    static int computeAdditional(int matches) {
        return Math.max(4-matches, matches & 1);
    }
}
