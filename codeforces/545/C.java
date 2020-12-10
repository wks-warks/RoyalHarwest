//Codeforces 545C 
import java.util.Scanner;

public class CF545C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int trees = SC.nextInt();
        int[][] posAndHeight = new int[trees][2];
        for (int t = 0; t < trees; ++t) {
            posAndHeight[t][0] = SC.nextInt();
            posAndHeight[t][1] = SC.nextInt();
        }
        int maxTreesCut = computeMaxTreesCut(trees, posAndHeight);
        System.out.println(maxTreesCut);
    }

    // Computes and returns maximum trees cut;
    static int computeMaxTreesCut(int trees, int[][] posAndHeight) {
        int felledTrees = 0;

        int prev = - 1_000_000_000;
        for (int i = 0; i < trees-1; ++i) {
            if (posAndHeight[i][0] - prev > posAndHeight[i][1]) {
                felledTrees += 1;
                prev = posAndHeight[i][0];
            }
            else if (posAndHeight[i+1][0] - posAndHeight[i][0] > posAndHeight[i][1]) {
                felledTrees += 1;
                prev = posAndHeight[i][0] + posAndHeight[i][1];
            }
            else {
                prev = posAndHeight[i][0];
            }
        }
        felledTrees += 1; // Felling the last one to its right
        return felledTrees;
    }
}
