//Codeforces 1360D 
import java.util.Scanner;
import java.util.ArrayList;

public class CF1360D {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int toBuy = SC.nextInt();
            int availableTypes = SC.nextInt();
            int minPackagesBought = computeMinPackages(toBuy, availableTypes);
            System.out.println(minPackagesBought);
        }
    }

    // Computes and returns minimum number of packages of the same type that shall be bought by Polycard
    static int computeMinPackages(int toBuy, int availableTypes) {
        int bestType = 1;
        int limit = Math.min(availableTypes, (int)Math.sqrt(toBuy));
        for (int candidate = 1; candidate <= limit; ++candidate)
            if (toBuy % candidate == 0) {
                bestType = Math.max(candidate, bestType);
                if (toBuy / candidate <= availableTypes) {
                    bestType = toBuy / candidate;
                    break;
                }
            }
        return toBuy / bestType;
    }
}
