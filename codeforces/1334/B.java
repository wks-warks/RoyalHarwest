//Codeforces 1334B 
import java.util.Scanner;
import java.util.Arrays;

public class CF1334B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int people = SC.nextInt();
            int threshold = SC.nextInt();
            Integer[] wealth = new Integer[people];
            for (int p = 0; p < people; ++p)
                wealth[p] = SC.nextInt();
            int reformedRich = computeReformedRich(people, threshold, wealth);
            System.out.println(reformedRich);
        }
    }

    // Computes and returns the number of rich people after reforms
    static int computeReformedRich(int people, int threshold, Integer[] wealth) {
        Arrays.sort(wealth); // Since we don't care exactly WHO owns what wealth, the ordered format WILL help us
        int rich;
        long moneyWithRich = 0;
        // Traversing from the wealthy end
        for (rich = 0; rich < people; ++rich) {
            moneyWithRich += wealth[people-(rich+1)];
            if (moneyWithRich < (long)threshold * (rich+1))
                break;
        }
        return rich;
    }
}