// Codeforces 431A
import java.util.Scanner;

public class CF431A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int[] costs = new int[4];
        for (int i = 0; i < 4; ++i)
            costs[i] = SC.nextInt();
        String gameSequence = SC.next(); // Contains sequence of strips appearing in the game
        int caloriesWasted = computeCaloriesWasted(costs, gameSequence);
        System.out.println(caloriesWasted);
    }

    // Computes and returns number of calories wasted
    static int computeCaloriesWasted(int[] costs, String gameSequence) {
        int calories = 0;
        for (int i = 0; i < gameSequence.length(); ++i)
            switch(gameSequence.charAt(i)) {
                case '1':
                    calories += costs[0];
                    break;
                case '2':
                    calories += costs[1];
                    break;
                case '3':
                    calories += costs[2];
                    break;
                case '4':
                    calories += costs[3];
            }
        return calories;
    }
}