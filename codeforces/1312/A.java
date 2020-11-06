//Codeforces 1312A 
import java.util.Scanner;

public class CF1312A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int v1 = SC.nextInt(); // Vertices of first polygon
            int v2 = SC.nextInt(); // Vertices of internal polygon
            // By symmetry, for every vertex of v2, there must be equal number of vertices of the outer polygon between any two vertices of the internal polygon
            boolean isPossible = v1 % v2 == 0;
            out(isPossible);
        }
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
