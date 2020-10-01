// Codeforces 231A
import java.util.Scanner;

public class CF231A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numProbs = SC.nextInt(); // number of problems
        int members = 3;
        int implementedProblems = 0;

        for (int p = 0; p < numProbs; ++p) {
            int[] surety = new int[members];
            for (int m = 0; m < members; ++m) {
                surety[m] = SC.nextInt(); // surety of m-th member
            }

            boolean willImplement = isTeamSure(surety);
            if (willImplement)
                implementedProblems += 1;
        }
        System.out.println(implementedProblems);
    }

    // Checks and tells whether the team is sure enough to implement a problem
    static boolean isTeamSure(int[] surety) {
        int sureMembers = 0; // number of members sure of solution
        for (int m = 0; m < surety.length; ++m) {
            if (surety[m] == 1)
                sureMembers += 1;
        }
        if (sureMembers >= 2)
            return true;
        else
            return false;
    }
}