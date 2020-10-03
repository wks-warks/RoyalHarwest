// Codeforces 617A
import java.util.Scanner;

public class CF617A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int friendsHouse = SC.nextInt();
        int stepsRequired = computeStepsRequired(friendsHouse);
        System.out.println(stepsRequired);
    }

    // Computes and returns minimum number of steps required to reach friend's house
    static int computeStepsRequired(int friendsHouse) {
        return (friendsHouse + 4) / 5; 
        // Moves 5 positions each time, until the destination is < 5 positions away
        // Reaches destination in 0 or 1 moves thereafter
    }
}