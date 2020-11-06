//Codeforces 124A 
import java.util.Scanner;

public class CF124A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int people = SC.nextInt(); // Total number of people
        int minAhead = SC.nextInt(); // Minimum number of people in front
        int maxBehind = SC.nextInt(); // Maximum number of people behind
        int positionsPossible = computePositionsPossible(people, minAhead, maxBehind);
        System.out.println(positionsPossible);
    }

    // Computes and returns number of positoins possible
    static int computePositionsPossible(int people, int minAhead, int maxBehind) {
        return Math.min(maxBehind+1, people-minAhead);
    }
}
