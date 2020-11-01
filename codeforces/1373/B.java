//Codeforces 1373B 
import java.util.Scanner;

public class CF1373B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            String binString = SC.next();
            int rounds = countRoundsPossible(binString)+1; // Number of playable rounds of the game
            boolean aliceWins = checkIfAliceWins(rounds);
            if (aliceWins)
                System.out.println("DA");
            else
                System.out.println("NET");
        }
    }

    // Checks and returns whether or not alice wins
    static boolean checkIfAliceWins(int rounds) {
        return rounds % 2 == 0; // Only if an even number of rounds are played would Alice win
    }

    // Counts and returnds number of rounds playable
    static int countRoundsPossible(String binString) {
        int zeroes = 0;
        int ones = 0;
        for (int i = 0; i < binString.length(); ++i)
            if (binString.charAt(i) == '1')
                ones += 1;
            else
                zeroes += 1;
        return Math.min(zeroes, ones);
    }
}
