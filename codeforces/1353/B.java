// Codeforces 1353B
import java.util.Scanner;
import java.util.Arrays;

public class CF1353B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt(); // Number of testCases
        for (int t = 0; t < tests; ++t) {
            int arrLen = SC.nextInt();
            int swapLimit = SC.nextInt(); // Number of swaps allowed
            
            int[] arrMaximize = new int[arrLen];
            for (int i = 0; i < arrLen; ++i)
                arrMaximize[i] = SC.nextInt();
            int[] arrBuffer = new int[arrLen];
            for (int i = 0; i < arrLen; ++i)
                arrBuffer[i] = SC.nextInt();
            int maxSum = getMaxSum(arrMaximize, arrBuffer, swapLimit);
            System.out.println(maxSum);
        }
    }

    // Computes and returns maximum possible sum of arrMaximize after swaps
    static int getMaxSum(int[] arrMaximize, int[] arrBuffer, int swapLimit) {
        int maxSum = 0; // Maximum sum when considering swaps
        Arrays.sort(arrMaximize);
        Arrays.sort(arrBuffer);
        int swapsLeft = swapLimit;
        int bufferIdx = arrBuffer.length - 1;
        for (int i = 0; i < arrMaximize.length; ++i)
            if (swapsLeft > 0 && arrBuffer[bufferIdx] > arrMaximize[i]) { // Swapping possible and beneficial => should swap
                swapsLeft -= 1;
                maxSum += arrBuffer[bufferIdx--];
            }
            else
                maxSum += arrMaximize[i];
        return maxSum;
    }

}