// Codeforces 230A
import java.util.Scanner;
import java.util.Arrays;

public class CF230A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int initStrength = SC.nextInt();
        int dragons = SC.nextInt();
        int[][] dragonDetails = new int[dragons][2];
        for (int d = 0; d < dragons; ++d) {
            dragonDetails[d][0] = SC.nextInt(); // Dragon Strength
            dragonDetails[d][1] = SC.nextInt(); // Dragon Bonus
        }
        boolean KiritoWins = canKiritoWin(initStrength, dragonDetails);
        out(KiritoWins);
    }

    // Checks and returns boolean whether or not Kirito can defeat all the dragons
    static boolean canKiritoWin(int initStrength, int[][] dragonDetails) {
        int strength = initStrength;
        // Sorting, as we would like to fight the dragons in ascending order of strength
        Arrays.sort(dragonDetails, (a, b) -> a[0] - b[0]);
        for (int d = 0; d < dragonDetails.length; ++d) {
            if (strength > dragonDetails[d][0])
                strength += dragonDetails[d][1]; // Adding bonus
            else
                return false;
        }
        return true; // Defeated all dragons
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}