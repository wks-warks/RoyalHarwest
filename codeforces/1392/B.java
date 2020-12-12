//Codeforces 1392B 
import java.util.Scanner;
import java.util.Arrays;
import java.util.IntSummaryStatistics;

public class CF1392B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = SC.nextInt();
            int k = (int) (SC.nextLong() % 2);
            int[] arr = new int[n];
            for (int i = 0; i <  n; ++i)
                arr[i] = SC.nextInt();
            
            int[] solutionArr = getSolutionArr(arr, k);
            print(solutionArr);
        }
    }

    // Prints given array
    static void print(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Computes and returns solution array
    static int[] getSolutionArr(int[] arr, int k) {
        IntSummaryStatistics stat = Arrays.stream(arr).summaryStatistics();
        int min = stat.getMin();
        int max = stat.getMax();
        
        int[] solutionArr = arr.clone();
        if (k == 1) {
            for (int i = 0; i < solutionArr.length; ++i) {
                solutionArr[i] = max - solutionArr[i];
            }
        }
        else {
            for (int i = 0; i < solutionArr.length; ++i) {
                solutionArr[i] = solutionArr[i] - min;
            }
        }
        return solutionArr;
    }
}
