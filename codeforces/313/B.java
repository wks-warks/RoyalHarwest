// Codeforces 313B
import java.util.Scanner;

public class CF313B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String str = SC.next();
        int queries = SC.nextInt();
        int[][] queryLR = new int[queries][2];
        for (int q = 0; q < queries; ++q) {
            queryLR[q][0] = SC.nextInt();
            queryLR[q][1] = SC.nextInt();
        }
        int[] solutionArray = getQueryResponses(str, queryLR);
        out(solutionArray);
    }

    // Computes and returns array with responses to the queries
    static int[] getQueryResponses(String str, int[][] queryLR) {
        int[] answersUptoR = new int[str.length()];
        for (int i = 1; i < answersUptoR.length; ++i)
            if (str.charAt(i) == str.charAt(i-1))
                answersUptoR[i] = answersUptoR[i-1] + 1;
            else
                answersUptoR[i] = answersUptoR[i-1];
        int[] queryResponses = new int[queryLR.length];
        for (int q = 0; q < queryLR.length; ++q) {
            int l0 = queryLR[q][0]-1; // 0-based indexing for l
            int r0 = queryLR[q][1]-1; // 0-based indexing for r
            queryResponses[q] = answersUptoR[r0] - answersUptoR[l0];
        }
        return queryResponses;
    }

    // Prints output corresponding to given array
    static void out(int[] array) {
        for (int i = 0; i < array.length; ++i)
            System.out.println(array[i]);
    }
}