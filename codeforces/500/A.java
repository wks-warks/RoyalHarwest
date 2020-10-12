// Codeforces 500A
import java.util.Scanner;

public class CF500A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int cells = SC.nextInt();
        int destination = SC.nextInt();
        int[] jumps = new int[cells-1];
        for (int i = 0; i < jumps.length; ++i)
            jumps[i] = SC.nextInt();
        boolean canReachDestination = checkDestinationReachability(jumps, destination-1); // Converting to 0-Idx
        out(canReachDestination);
    }

    // Checks and returns boolean whether the given destination is reachable
    static boolean checkDestinationReachability(int[] jumps, int destination) {
        int pos = 0; // 0-Idx start
        while (pos < jumps.length) { // jumps.length = n-1 (0-Idx End of LineWorld)
            if (pos == destination)
                return true;
            else
                pos += jumps[pos];
        }
        return pos == destination; // Destination reached at end or couldn't reach destination
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}