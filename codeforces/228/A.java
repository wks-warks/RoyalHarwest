// Codeforces 228A
import java.util.Scanner;
import java.util.Arrays;

public class CF228A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int[] colours = new int[4];
        for (int h = 0; h < 4; ++h)
            colours[h] = SC.nextInt();
        int horseshoesNeeded = computeHorseshoesNeeded(colours);
        System.out.println(horseshoesNeeded);
    }

    // Computes and returns number of horseshoes to be bought
    static int computeHorseshoesNeeded(int[] colours) {
        return colours.length - (int)Arrays.stream(colours).distinct().count();
        // Differently coloured horseshoes: totalRequired - presentlyAvailable
    }
}