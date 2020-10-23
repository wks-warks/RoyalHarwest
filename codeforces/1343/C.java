// Codeforces 1343C
import java.util.Scanner;
import java.util.Vector;

public class CF1343C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = SC.nextInt();
            int[] arr = new int[arrLen];
            for (int i = 0; i < arrLen; ++i)
                arr[i] = SC.nextInt();
            long solutionSum = getSolutionSum(arr);
            System.out.println(solutionSum);
        }
    }

    // Computes and returns sum of longest alternating sequence with maximum sum
    static long getSolutionSum(int[] arr) {
        Vector<Integer> solution = new Vector<>();
        boolean prevPos = arr[0] > 0;
        int appendSolution = arr[0]; // Candidate number to be appended to solution vector
        for (int i = 0; i < arr.length; ++i) {
            boolean isPos = arr[i] > 0;
            if (prevPos) {
                if (isPos) {
                    appendSolution = Math.max(arr[i], appendSolution);
                }
                else {
                    solution.add(appendSolution);
                    appendSolution = arr[i];
                    prevPos = false;
                }
            }
            else {
                if (isPos) {
                    solution.add(appendSolution);
                    appendSolution = arr[i];
                    prevPos = true;
                }
                else {
                    appendSolution = Math.max(appendSolution, arr[i]);
                }
            }
        }
        //Adding last element
        solution.add(appendSolution);
        
        return vectorSum(solution);
    }

    // Returns sum of elements of vector
    static long vectorSum(Vector<Integer> v) {
        long sum = 0;
        for (int num : v)
            sum += num;
        return sum;
    }
}