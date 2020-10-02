// Codeforces 266A
import java.util.Scanner;

public class CF266A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numStones = SC.nextInt(); // number of stones
        String stones = SC.next();
        int stonesRemoved = computeStonesToBeRemoved(stones);
        System.out.println(stonesRemoved);
    }

    // Computes stones to be removed
    static int computeStonesToBeRemoved(String stones) {
        int removed = 0;
        char prev = ' ';
        for (int i = 0; i < stones.length(); ++i) {
            char present = stones.charAt(i);
            if (present == prev) // remove stones that are the same as their preceding one
                removed += 1;
            prev = present;
        }
        return removed;
    }
}