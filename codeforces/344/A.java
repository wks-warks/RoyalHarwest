// Codeforces 344A
import java.util.Scanner;

public class CF344A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numMagnets = SC.nextInt(); // Number of magnets
        String[] orientations = new String[numMagnets];
        for (int m = 0; m < numMagnets; ++m)
            orientations[m] = SC.next();
        int numGroups = computeNumberOfGroups(orientations);
        System.out.println(numGroups);
    }

    // Computes and returns number of groups formed
    static int computeNumberOfGroups(String[] orientations) {
        String prev = "";
        int groups = 0;
        for (int m = 0; m < orientations.length; ++m) {
            String cur = orientations[m];
            if (!cur.equals(prev)) // If current not equal to previous
                groups += 1;
            prev = cur;
        }
        return groups;
    }
}