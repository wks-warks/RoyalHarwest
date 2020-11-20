//Codeforces 1334A 
import java.util.Scanner;

public class CF1334A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int moments = SC.nextInt();
            int[] plays = new int[moments+1];
            int[] clears = new int[moments+1];
            for (int m = 1; m <= moments; ++m) {
                plays[m] = SC.nextInt();
                clears[m] = SC.nextInt();
            }
            boolean possible = checkPossibility(moments, plays, clears);
            out(possible);
        }
    }

    // Checks and returns whether or not the given records are possible
    static boolean checkPossibility(int moments, int[] plays, int[] clears) {
        for (int m = 1; m <= moments; ++m) {
            boolean tooManyClears = clears[m] - clears[m-1] > plays[m] - plays[m-1] || clears[m] < clears[m-1];
            boolean tooManyPlays = plays[m] < plays[m-1];
            if (tooManyClears || tooManyPlays)
                return false;
        }
        return true; // No clash found
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
