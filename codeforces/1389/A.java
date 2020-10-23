// Codeforces 1389A
import java.util.Scanner;

public class CF1389A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int left = SC.nextInt();
            int right = SC.nextInt();
            Pair solution = computeXY(left, right);
            System.out.println(solution.getX() + " " + solution.getY());
        }
    }

    // Computes and returns pair of integers x, y according to requirements
    static Pair computeXY(int left, int right) {
        if (left * 2 > right)
            return (new Pair(-1, -1)); // No solution exists
        else
            return (new Pair(left, 2*left)); // Smallest possible pair satisfying requirements
    }
}

class Pair {
    private int x;
    private int y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}