// Codeforces 1358A
import java.util.Scanner;

public class CF1358A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int length = SC.nextInt();
            int breadth = SC.nextInt();
            int lanterns = computeMinimumLanterns(length, breadth);
            System.out.println(lanterns);
        }
    }

    // Computes and returns the minimum number of lanterns required to light all squares
    static int computeMinimumLanterns(int length, int breadth) {
        return (length * breadth + 1)/ 2;
        // Since one lantern can light upto two sqaures, we find that in case of even length/breadth, lanterns = squares/2, and otherwise, lanterns = squares/2 + 1
    }
}