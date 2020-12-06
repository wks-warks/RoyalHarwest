//Codeforces 1450 B 
import java.util.*;

public class CFB {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int balls,attractPower, x, y, b, tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            balls = SC.nextInt();
            attractPower = SC.nextInt();
            Pair[] positions = new Pair[balls];
            for (b = 0; b < balls; ++b) {
                x = SC.nextInt();
                y = SC.nextInt();
                positions[b] = new Pair(x, y);
            }
            
            int minOperations = countMinOperations(attractPower, positions, balls);
            System.out.println(minOperations);
        }
    }

    // Computes and returns minimum number of operations required to get all balls at the same pos
    static int countMinOperations(int attractPower, Pair[] positions, int balls) {
        for (int ballConsidered = 0; ballConsidered < balls; ++ballConsidered) {
            int reachable = 0;
            for (int b = 0; b < balls; ++b) {
                if (Math.abs(positions[b].x - positions[ballConsidered].x) + Math.abs(positions[b].y - positions[ballConsidered].y) <= attractPower) {
                    reachable += 1;
                }
            }
            if (reachable == balls)
                return 1;
        }
        return -1;
    }
}

class Pair {
    int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}