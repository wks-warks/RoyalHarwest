//Codeforces 1406A 
import java.util.Scanner;

public class CF1406A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int setSize = SC.nextInt();
            int[] set = new int[setSize];
            for (int i = 0; i < setSize; ++i) {
                set[i] = SC.nextInt();
            }
            int maxMex = computeMaxMex(set);
            System.out.println(maxMex);
        }
    }

    // Computes and returns maximum mex value
    static int computeMaxMex(int[] set) {
        int[] numOccurrences = new int[101];
        for (int i = 0; i < set.length; ++i) {
            numOccurrences[set[i]] += 1;
        }

        int mexFound = 0;
        int answer = 0;
        loop :
        for (int i = 0; i < numOccurrences.length; ++i) {
            if (numOccurrences[i] < 2 - mexFound) {
                int found = 2 - Math.max(mexFound, numOccurrences[i]);
                answer += found * i;
                mexFound += found;
            }
        }
        answer += (2 - mexFound) * 101;

        return answer;
    }
}
