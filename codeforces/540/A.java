// Codeforces 540A
import java.util.Scanner;

public class CF540A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int disks = SC.nextInt();
        String initial = SC.next(); // Initial state
        String desired = SC.next(); // Desired state
        int movesToOpen = computeMovesRequired(initial, desired);
        System.out.println(movesToOpen);
    }

    // Computes and returns number of moves required to obtain the desired state from the present one
    static int computeMovesRequired(String initial, String desired) {
        int movesRequired = 0;
        for (int i = 0; i < initial.length(); ++i)
            movesRequired += movesForDisk(initial.charAt(i), desired.charAt(i)); // Adding moves required for given disk to total 
        return movesRequired;
    }

    // Computes and returns number of moves required to get present disk to the desirable state
    static int movesForDisk(char initial, char desired) {
        int case1 = Math.max(initial, desired) - Math.min(initial, desired);
        int case2 = 10 - case1;
        return Math.min(case1, case2);
    }
}