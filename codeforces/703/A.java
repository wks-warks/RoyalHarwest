// Codeforces 703A
import java.util.Scanner;

public class CF703A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int rounds = SC.nextInt();
        int[] MishkaThrow = new int[rounds];
        int[] ChrisThrow = new int[rounds];
        for (int r = 0; r < rounds; ++r) {
            MishkaThrow[r] = SC.nextInt();
            ChrisThrow[r] = SC.nextInt();
        }
        String result = getResult(MishkaThrow, ChrisThrow);
        System.out.println(result);
    }

    // Gets Result-string based on who wins, computed using MishkaThrow and ChrisThrow
    static String getResult(int[] MishkaThrow, int[] ChrisThrow) {
        int roundsMishkaWins = 0;
        int roundsChrisWins = 0;
        for (int i = 0; i < MishkaThrow.length; ++i)
            if (MishkaThrow[i] > ChrisThrow[i])
                roundsMishkaWins += 1;
            else if (MishkaThrow[i] < ChrisThrow[i])
                roundsChrisWins += 1;
        if (roundsMishkaWins > roundsChrisWins)
            return "Mishka";
        else if (roundsChrisWins > roundsMishkaWins)
            return "Chris";
        else
            return "Friendship is magic!^^";
    }
}