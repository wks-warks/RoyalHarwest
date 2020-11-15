//Codeforces 732B 
import java.util.Scanner;

public class CF732B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int days = SC.nextInt();
        int walksPerTwoDays = SC.nextInt();
        int[] walks = new int[days];
        for (int d = 0; d < days; ++d)
            walks[d] = SC.nextInt();
        int additionalWalksNeeded = computeAdditionalWalksNeeded(days, walksPerTwoDays, walks);
        out(additionalWalksNeeded, walks);
    }

    // Computes and returns the number of additional walks needed to satisfy Cormen
    static int computeAdditionalWalksNeeded(int days, int walksPerTwoDays, int[] walks) {
        int add, additionalWalks = 0;
        for (int d = 1; d < days; ++d) {
            add = Math.max(0, walksPerTwoDays-(walks[d] + walks[d-1]));
            walks[d] += add;
            additionalWalks += add;
        }
        return additionalWalks;
    }

    // Prints output corresponding to response
    static void out(int additionalWalksNeeded, int[] walks) {
        System.out.println(additionalWalksNeeded);
        for (int d = 0; d < walks.length; ++d)
            System.out.print(walks[d] + " ");
        System.out.println();
    }
}
