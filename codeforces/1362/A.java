//Codeforces 1362A 
import java.util.Scanner;

public class CF1362A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            long init = SC.nextLong();
            long desired = SC.nextLong();
            int moves = countMoves(init, desired);
            solution.append(moves + "\n");
        }
        System.out.print(solution.toString());
    }

    // Computes and returns number of moves required to get init to desired
    static int countMoves(long init, long desired) {
        if (desired < init) {
            long temp = desired;
            desired = init;
            init = temp;
        }
        if (desired % init != 0)
            return -1;
        long toCover = desired / init;
        int moves = 0;
        while (toCover > 1) {
            if ((toCover >> 3) << 3  == toCover) {
                toCover >>= 3;
                moves += 1;
            }
            else if ((toCover >> 2) << 2 == toCover) {
                toCover >>= 2;
                moves += 1;
            }
            else if ((toCover & 1) == 0) {
                toCover >>= 1;
                moves += 1;
            }
            else
                return -1;
        }
        return moves;
    }
}
