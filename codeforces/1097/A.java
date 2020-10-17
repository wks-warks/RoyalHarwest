// Codeforces 1097A
import java.util.Scanner;

public class CF1097A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String tableCard = SC.next();
        String[] yourCards = new String[5];
        for (int i = 0; i < 5; ++i)
            yourCards[i] = SC.next();
        boolean canPlay = checkIfPlayable(tableCard, yourCards);
        out(canPlay);
    }

    // Checks and returns boolean whether or not a card can be played based on the cards present
    static boolean checkIfPlayable(String tableCard, String[] yourCards) {
        boolean playable = false;
        char tableRank = tableCard.charAt(0);
        char tableSuit = tableCard.charAt(1);
        for (int i = 0; i < yourCards.length; ++i) {
            char rank = yourCards[i].charAt(0);
            char suit = yourCards[i].charAt(1);
            playable |= rank == tableRank || suit == tableSuit;
        }
        return playable;
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("yes");
        else
            System.out.println("no");
    }
}