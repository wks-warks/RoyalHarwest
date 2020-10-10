// Codeforces 155A
import java.util.Scanner;

public class CF155A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int contests = SC.nextInt(); // Number of contests;
        int[] scores = new int[contests]; // Scores in the contests
        for (int c = 0; c < contests; ++c)
            scores[c] = SC.nextInt();
        int amazingContets = countAmazingContests(scores);
        System.out.println(amazingContets);
    }

    // Counts and returns number of contests that can be considered amazing
    static int countAmazingContests(int[] scores) {
        int min = scores[0];
        int max = scores[0];
        int amazing = 0; // Number of amazing contests
        for (int i = 1; i < scores.length; ++i)
            if (scores[i] > max) {
                max = scores[i];
                amazing += 1;
            }
            else if ( scores[i] < min) {
                min = scores[i];
                amazing += 1;
            }
        return amazing;
    }
}