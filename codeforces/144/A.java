// Codeforces 144A
import java.util.Scanner;

public class CF144A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int soldiers = SC.nextInt(); // Number of soldiers
        int[] solHts = new int[soldiers]; // Soldiers' heights
        for (int s = 0; s < soldiers; ++s)
            solHts[s] = SC.nextInt();
        int swapsRequired = computeSwapsRequired(solHts); // Minimum number of swaps required to fool the General
        System.out.println(swapsRequired);
    }

    // Computes and returns minimum number of swaps required
    static int computeSwapsRequired(int[] heights) {
        // Send tallest soldier to idx-0 and shortest one to last-idx
        int idxLargest = getIdxLargest(heights);
        int idxSmallest = getIdxSmallest(heights);
        int swaps = idxLargest + (heights.length-1 - idxSmallest);
        if (idxSmallest < idxLargest)
            swaps -= 1; // Since there is one common swap
        return swaps;
    }

    // Gets idx of first instance of largest number
    static int getIdxLargest(int[] arr) {
        int maxVal = Integer.MIN_VALUE;
        int maxIdx = 0;
        for (int i = 0; i < arr.length; ++i)
            if (arr[i] > maxVal) {
                maxVal = arr[i];
                maxIdx = i;
            }
        return maxIdx;
    }

    // Gets idx of last instance of smallest number
    static int getIdxSmallest(int[] arr) {
        int minVal = Integer.MAX_VALUE;
        int minIdx = 0;
        for (int i = 0; i < arr.length; ++i)
            if (arr[i] <= minVal) {
                minVal = arr[i];
                minIdx = i;
            }
        return minIdx;
    }
}