// Codeforces 451A
import java.util.Scanner;

public class CF451A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int n = SC.nextInt();
        int m = SC.nextInt();
        String winner = whoWins(n, m);
        System.out.println(winner);
    }

    // Finds out winner and returns name
    static String whoWins(int n, int m) {
        int decidingDimension = Math.min(n, m); // One stick of each dimension is being removed at each step. The dimension that is used up first, will decide the winner.
        if (decidingDimension % 2 == 0)
            return "Malvika";
        else
            return "Akshat";
    }
}