// Codeforces 959A
import java.util.Scanner;

public class CF959A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int n = SC.nextInt();
        String winner = getWinner(n);
        System.out.println(winner);
    }

    // Computes and returns name of the winner
    static String getWinner(int n) {
        if (n % 2 == 1)
            return "Ehab";
        else
            return "Mahmoud";
    }
}