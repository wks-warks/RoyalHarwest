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
        // Direct Method
        return (int)(((long)buttons*buttons*buttons+5*buttons)/6);
    }
}

// Sequential method
/* 
        int movesRequired = 0;
        for (int sequenceFound = 0; sequenceFound < buttons; ++sequenceFound) {
            int sequenceRemaining = buttons - sequenceFound;
            movesRequired += 1 + (sequenceFound+1) * (sequenceRemaining-1);
        }
        return movesRequired;
*/

/*
sequenceRemaining = buttons
    Sigma (1 + i*(n-i)) over 1 to n
    = n + n*Sigma(i) - Sigma(i*i)
    = n + n*(n)*(n+1)/2 - (n)(n+1)(2n+1)/6
    = 6n + 3n3 +3n2 - 2n3-3n2-n
    = (n3 +5n)/6
*/