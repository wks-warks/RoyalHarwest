//Codeforces 832A 
import java.util.Scanner;

public class CF832A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        long sticks = SC.nextLong();
        long remove = SC.nextLong();
        String sashaWins = doesSashaWin(sticks, remove);
        System.out.println(sashaWins);
    }

    // Checks and returns yes/no depending on whether or not sasha wins
    static String doesSashaWin(long sticks, long remove) {
        long moves = sticks / remove;
        return moves % 2 == 0 ? "NO" : "YES";
    }
}
