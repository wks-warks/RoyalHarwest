//Codeforces 1433B 
import java.util.Scanner;

public class CF1433B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int shelves = SC.nextInt();
            int[] bookPresence = new int[shelves];
            for (int s = 0; s < shelves; ++s)
                bookPresence[s] = SC.nextInt();
            int minMoves = computeMinMoves(bookPresence);
            System.out.println(minMoves);
        }
    }

    // Computes and returns minimum number of moves required to set the shelf as per requirement
    static int computeMinMoves(int[] bookPresence) {
        int moves = 0;
        boolean foundFirst = false;
        int movesToLeft = 0;
        for (int i = 0; i < bookPresence.length; ++i)
            if (bookPresence[i] == 0)
                moves += 1;
            else if (!foundFirst) {
                foundFirst = true;
                moves = 0;
            }
            else {
                movesToLeft += moves;
                moves = 0;
            }

        foundFirst = false;
        moves = 0;
        int movesToRight = 0;
        for (int i = bookPresence.length-1; i >= 0; --i)
            if (bookPresence[i] == 0)
                moves += 1;
            else if (!foundFirst) {
                foundFirst = true;
                moves = 0;
            }
            else {
                movesToRight += moves;
                moves = 0;
            }
            
        return Math.min(movesToLeft, movesToRight);
    }
}
