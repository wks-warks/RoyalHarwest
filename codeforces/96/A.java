// Codeforces 96A
import java.util.Scanner;

public class CF96A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String players = SC.next();
        boolean isDangerous = checkDangerous(players);
        out(isDangerous);
    }

    // Checks if the given orientation of players is dangerous
    static boolean checkDangerous(String players) {
        int continousSame = 0;
        char prev = ' ';
        for (int p = 0; p < players.length(); ++p) {
            char present = players.charAt(p);
            if (present == prev)
                continousSame += 1;
            else {
                continousSame = 1;
                prev = present;
            }
            if (continousSame == 7) // No need to check further
                return true;
        }
        return false;
    }

    // Prints output corresponding to boolean state
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}