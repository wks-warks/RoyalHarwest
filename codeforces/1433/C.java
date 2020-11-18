//Codeforces 1433C 
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class CF1433C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        StringBuilder solution = new StringBuilder();
        for (int t = 0; t < tests; ++t) {
            int piranhas = SC.nextInt();
            Integer[] strengths = new Integer[piranhas];
            for (int p = 0; p < piranhas; ++p)
                strengths[p] = SC.nextInt();
            int domPiranhaIdx = getDomPiranhaIdx(piranhas, strengths);
            solution.append(domPiranhaIdx + "\n");
        }
        System.out.print(solution);
    }

    // Computes and returns index of dominant piranha
    static int getDomPiranhaIdx(int piranhas, Integer[] strengths) {
        int strongest = Collections.max(Arrays.asList(strengths));
        for (int p = 0; p < piranhas; ++p)
            if (strengths[p] == strongest)
                if (p != 0 && strengths[p-1] != strongest)
                    return p+1; // 1-based-idx
                else if (p != piranhas-1 && strengths[p+1] != strongest)
                    return p+1; // 1-based-idx
        return -1; // All piranhas had the same strength
    }
}
