//Codeforces 1385C 
import java.util.Scanner;

public class CF1385C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests  = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = SC.nextInt();
            int[] arr = new int[arrLen];
            for (int i = 0; i < arrLen; ++i) {
                arr[i] = SC.nextInt();
            }
            int minPrefix = computeMinPrefix(arr);
            solution.append(minPrefix + "\n");
        }
        System.out.print(solution.toString());
    }

    // Computes and returns the smallest prefix length that is to be removed to get a good array
    static int computeMinPrefix(int[] arr) {
        int[] maximumR = new int[arr.length]; // maximum ai from right end
        maximumR[arr.length-1] = arr[arr.length-1];
        for (int i = arr.length-2; i >= 0; --i)
            maximumR[i] = Math.max(arr[i], maximumR[i+1]);
 
        int solutionIdx = arr.length-1;
        int right = arr[arr.length-1];
        for (int i = arr.length-2; i >= 0; --i) {
            if (arr[i+1] != maximumR[i+1] && arr[i] > arr[i+1])
                break;
            else
                solutionIdx = i;
        }
        return solutionIdx;
    }
}
