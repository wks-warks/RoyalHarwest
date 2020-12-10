//Codeforces 1452A 
import java.util.Scanner;

public class CF1452A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int x = SC.nextInt();
            int y = SC.nextInt();
            int movesRequired = computeMovesRequired(x, y);
            System.out.println(movesRequired);
        }
    }

    // Computes and returns moves required
    static int computeMovesRequired(int x, int y) {
        int lesser = Math.min(x, y);
        int greater = Math.max(x, y);
        
        int movesRequired = 2 * lesser;
        greater -= lesser;
        if (greater > 0) {
            greater -= 1;
            movesRequired += 1;
        }

        movesRequired += greater * 2; // Say we need to go 4 right more, RURDRURD works
        return movesRequired;
    }
}
