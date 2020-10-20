// Codeforces 327A
import java.util.Scanner;
 
public class CF327A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int n = SC.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i)
            arr[i] = SC.nextInt();
        int maxOnesAfterOne = computeOnesAfterMove(arr);
        System.out.println(maxOnesAfterOne);
    }
 
    // Computes and returns maximum number of ones possible after one flip
    static int computeOnesAfterMove(int[] arr) {
        int[] gainAfterFlip = new int[arr.length];
        gainAfterFlip[0] = arr[0] == 0? 1: -1; // 1 if arr[0] is 0, -1 otherwise
        for (int i = 1;i < arr.length; ++i)
            if (arr[i] == 0)
                gainAfterFlip[i] = Math.max(gainAfterFlip[i-1], 0) + 1;
            else
                gainAfterFlip[i] = gainAfterFlip[i-1] - 1;
        int initialOnes = countOnes(arr);
        int additionalOnes = computeMaxGain(gainAfterFlip);
        if (additionalOnes != 0)
            return initialOnes + additionalOnes;
        else
            return initialOnes - 1;
    }
 
    // Computes and returns number of ones in the array
    static int countOnes(int[] arr) {
        int ones = 0;
        for (int i = 0; i < arr.length; ++i)
            if (arr[i] == 1)
                ones += 1;
        return ones;
    }
 
    // Computes and returns max gain possible
    static int computeMaxGain(int[] gains) {
        int maxGain = 0;
        for (int i = 0; i < gains.length; ++i)
            maxGain = Math.max(maxGain, gains[i]);
        return maxGain;
    }
}