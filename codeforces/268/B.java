//Codeforces 268B 
import java.util.Scanner;

public class CF268B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int buttons = SC.nextInt();
        int movesRequired = computeMovesRequired(buttons);
        System.out.println(movesRequired);
    }

    // Computes and returns number of moves required in worst case
    static int computeMovesRequired(int buttons) {
        int movesRequired = 0;
        for (int sequenceFound = 0; sequenceFound < buttons; ++sequenceFound) {
            int sequenceRemaining = buttons - sequenceFound;
            movesRequired += 1 + (sequenceFound+1) * (sequenceRemaining-1);
        }
        return movesRequired;
    }
}
