// Codeforces 734A
import java.util.Scanner;

public class CF734A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numGames = SC.nextInt();
        String results = SC.next();
        String outcome = getOutcome(numGames, results);
        System.out.println(outcome);
    }

    // Finds and returns the outcome
    static String getOutcome(int numGames, String results) {
        int winsAnton = 0; // No. of games won by Anton
        int winsDanik = 0; // No. of games won by Danik
        for (int g = 0; g < numGames; ++g) {
            char resultG = results.charAt(g); // winner of g-th game
            if (resultG == 'A')
                winsAnton += 1;
            else
                winsDanik += 1;
        }
        if (winsAnton > winsDanik)
            return "Anton";
        else if (winsAnton < winsDanik)
            return "Danik";
        else
            return "Friendship";
    }
}