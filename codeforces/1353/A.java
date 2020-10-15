// Codeforces 1353A
import java.util.Scanner;

public class CF1353A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int length = SC.nextInt(); // Length of array
            int arrSum = SC.nextInt(); // Sum of elements of array
            int maxDiffSum = computeMaxDiffSum(length, arrSum);
            System.out.println(maxDiffSum);
        }
    }

    // Computes and returns the maximum value of the sum of absolute differences for the array that allows the maximum such sum
    static int computeMaxDiffSum(int length, int arrSum) {
        /*
        * There are 3 cases -
        * Case 1: length = 1 => return 0 
        * Case 2: length = 2 => return arrSum. Array with best diffSum = {0, sum}
        * Case 3: length > 3 => return 2*arrSum. Array with best diffSum = {0, sum, 0, ..}
        * Any other distribution will not lead to a greater diffSum value
        */
        return arrSum * Math.min(length-1, 2);
    }
}