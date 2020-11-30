//Codeforces 1364A 
import java.util.Scanner;

public class CF1364A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = SC.nextInt();
            int[] arr = new int[arrLen];
            int hatedNum = SC.nextInt();
            for (int i = 0; i < arrLen; ++i)
                arr[i] = SC.nextInt();
            
            int bestSubLength = computeBestSubLength(arr, hatedNum);
            System.out.println(bestSubLength);
        }
    }

    // Computes and returns length of longest subarray whose sum isn't divisible by hatedNum
    static int computeBestSubLength(int[] arr, int hatedNum) {
        int[] prefixSum = getPrefixSum(arr);        
        if (prefixSum[prefixSum.length-1] % hatedNum != 0)
            return prefixSum.length-1;

        int leftStart = 0;
        while (leftStart < arr.length && arr[leftStart] % hatedNum == 0)
            leftStart += 1;        
        if (leftStart == arr.length)
            return -1;

        int rightStart = arr.length-1;
        while (rightStart >= 0 && arr[rightStart] % hatedNum == 0)
            rightStart -= 1;

        return Math.max(arr.length - leftStart-1, rightStart);
    }

    // Computes and returns prefix sum array
    static int[] getPrefixSum(int[] arr) {
        int[] prefixSum = new int[arr.length+1];
        for (int i = 0; i < arr.length; ++i)
            prefixSum[i+1] = prefixSum[i] + arr[i];
        return prefixSum;
    }
}
