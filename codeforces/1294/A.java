// Codeforces 1294A
import java.util.Scanner;

public class CF1294A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int coinsAlice = SC.nextInt();
            int coinsBarbara = SC.nextInt();
            int coinsCerene = SC.nextInt();
            int coinsPolycarp = SC.nextInt();
            boolean distributionPossible = checkPossibleDistribution(coinsAlice, coinsBarbara, coinsCerene, coinsPolycarp);
            out(distributionPossible);
        }
    }

    // Checks and returns boolean whether a possible distribution exists
    static boolean checkPossibleDistribution(int a, int b, int c, int p) {
        int desiredForEach = Math.max(a, Math.max(b, c));
        return (a+b+c+p - 3*desiredForEach) >= 0 && (a+b+c+p - 3*desiredForEach) % 3 == 0; 
    }

    // Prints output corresponding to the condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}