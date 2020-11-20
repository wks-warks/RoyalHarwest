//Codeforces 1108A 
import java.util.Scanner;

public class CF1108A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int limL1 = SC.nextInt();
            int limR1 = SC.nextInt();
            int limL2 = SC.nextInt();
            int limR2 = SC.nextInt();
            System.out.println(limL1 + " " + (limL2 != limL1 ? limL2 : limR2));
        }
    }
}
