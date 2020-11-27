//Codeforces 1382B 
import java.util.Scanner;

public class CF1382B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int stonePiles = SC.nextInt();
            int[] pile = new int[stonePiles];
            for (int p = 0; p < stonePiles; ++p)
                pile[p] = SC.nextInt();
            String winner = computeWinner(stonePiles, pile);
            solution.append(winner + "\n");
        }
        System.out.print(solution.toString());
    }

    // Computes and returns who the winner will be on the basis of the number of piles and the amount of stones in them
    static String computeWinner(int stonePiles, int[] pile) {
        int decidingIdx = -1;
        for (int p = 0; p < stonePiles; ++p)
            if (pile[p] != 1) {
                decidingIdx = p;
                break;
            }

        if (decidingIdx != -1) { // Some player finds a means to control the game hereafter
            return decidingIdx % 2 == 0 ? "First" : "Second";
        }
        else {
            return stonePiles % 2 != 0 ? "First" : "Second";
        }
    }
}
