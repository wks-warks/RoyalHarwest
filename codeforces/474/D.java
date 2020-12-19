//Codeforces 474D 
import java.util.Scanner;

public class CF474D {
    static final Scanner SC = new Scanner(System.in);

    static int[] prefixSum;
    public static void main(String[] args) {
        int tests = SC.nextInt();
        int groupSize = SC.nextInt();
        setPrefixSum(groupSize);

        StringBuilder solution = new StringBuilder();
        for (int t = 0; t < tests; ++t) {
            int lowerLimit = SC.nextInt();
            int upperLimit = SC.nextInt();
            int answer = (prefixSum[upperLimit] - prefixSum[lowerLimit-1] + 1_000_000_007) % 1_000_000_007;
            solution.append(answer);
            solution.append('\n');
        }
        System.out.print(solution.toString());
    }

    // Finds answers and sets prefix sum values
    static void setPrefixSum(int groupSize) {
        int[] ways = new int[100_001];
        ways[0] = 1;
        for (int i = 0; i < ways.length; ++i) {
            if (i + 1 < ways.length)
                ways[i+1] = (ways[i+1] + ways[i]) % 1_000_000_007;
            if (i + groupSize < ways.length)
                ways[i+groupSize] = (ways[i+groupSize] + ways[i]) % 1_000_000_007;
        }
        
        prefixSum = new int[ways.length];
        prefixSum[0] = ways[0];
        for (int i = 1; i < prefixSum.length; ++i) {
            prefixSum[i] = (prefixSum[i-1] + ways[i]) % 1_000_000_007;
        }
    }
}
