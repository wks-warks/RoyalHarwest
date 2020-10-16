// Codeforces 270A
import java.util.Scanner;

public class CF270A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int interiorAngle = SC.nextInt();
            boolean possiblyRegularAngle = regularPolygonPossible(interiorAngle);
            out(possiblyRegularAngle);
        }
    }

    // Checks and returns boolean whether a regular polygon with given interior angle is possible
    static boolean regularPolygonPossible(int interiorAngle) {
        int exteriorAngle = 180 - interiorAngle;
        if (360 % exteriorAngle != 0)
            return false;
        int sides = 360 / exteriorAngle;
        return sides > 2;
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}