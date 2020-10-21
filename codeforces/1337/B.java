// Codeforces 1337B
import java.util.Scanner;

public class CF1337B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int hitPoints = SC.nextInt(); // Dragon's HP
            int absorptions = SC.nextInt(); // No. of Void Absorptions allowed
            int strikes = SC.nextInt(); // No. of Lightning strikes allowed
            boolean canDefeat = checkIfDefeatable(hitPoints, absorptions, strikes);
            out(canDefeat);
        }
    }

    // Checks and returns whether or not the dragon can be defeated
    static boolean checkIfDefeatable(int hitPoints, int absorptions, int strikes) {
        // It is best to utilize absorptions first as, higher the hp remaining - greater its impact
        while (hitPoints > 20 && absorptions > 0) {
            hitPoints = hitPoints/2 + 10;
            absorptions -= 1;
        }
        return hitPoints <= strikes * 10; // Finally, can remaining HP be finished using strikes
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}