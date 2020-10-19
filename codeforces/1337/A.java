// Codeforces 1337A
import java.util.Scanner;

public class CF1337A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int[] limiters = new int[4]; // a, b, c, d
            for (int i = 0; i < 4; ++i)
                limiters[i] = SC.nextInt();
            int[] sideLengths = getSideLengths(limiters);
            out(sideLengths);
        }
    }

    // Returns lengths of three sides
    static int[] getSideLengths(int[] limiters) {
        int[] sides = {limiters[1], limiters[2], limiters[2]}; // keeping the largest 2 same, the smallest is inconsequential
        return sides;
    }

    // Prints output corresponding to the array
    static void out(int[] sideLengths) {
        for (int i = 0; i < sideLengths.length; ++i)
            System.out.print(sideLengths[i] + " ");
        System.out.println();
    }
}