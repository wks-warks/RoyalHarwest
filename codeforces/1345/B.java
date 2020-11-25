//Codeforces 1345B 
import java.util.Scanner;

public class CF1345B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int cards = SC.nextInt();
            int pyramids = countPyramids(cards);
            solution.append(pyramids + "\n");
        }
        System.out.print(solution);
    }

    // Counts and returns the number of pyramids made with given number of cards
    static int countPyramids(int cards) {
        if (cards < 2)
            return 0;
        int used = countUsed(cards);
        return 1 + countPyramids(cards - used);
    }

    // Computes and returns number of cards to build the highest order pyramid possible with given cards
    static int countUsed(int cards) {
        // Performing binary search to get the max. cards used
        int used = 0;
        int left = 1;
        int right = cards / 2;
        while (left <= right) {
            long mid = (left+right)/2;
            long requirement = mid * (3*mid + 1) / 2;
            if (requirement > cards)
                right = (int)mid-1;
            else {
                used = (int)requirement;
                left = (int)mid + 1;
            }
        }
        return used;
    }
}
