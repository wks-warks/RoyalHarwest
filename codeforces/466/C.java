//Codeforces 466C 
import java.util.Scanner;

public class CF466C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int elements = SC.nextInt();
        int[] array = new int[elements];
        for (int i = 0; i < elements; ++i)
            array[i] = SC.nextInt();
        long ways = countWays(array);
        System.out.println(ways);
    }

    // Counts ways in which array can be divided into three parts with 
    static long countWays(int[] array) {
        long[] prefixSum = getPrefixSum(array);
        long totalSum = prefixSum[prefixSum.length-1];
        if (totalSum % 3 != 0 || array.length == 2)
            return 0;

        long sumByThree = totalSum/3;
        
        // End points for 1st segment add +1 to that index compared to the previous one
        int[] thirdsByLeft = new int[array.length];
        if (array[0] == sumByThree)
            thirdsByLeft[0] = 1;
        for (int i = 1; i < array.length-2; ++i)
            if (prefixSum[i] == sumByThree)
                thirdsByLeft[i] = thirdsByLeft[i-1] + 1;
            else
                thirdsByLeft[i] = thirdsByLeft[i-1];
        
        // End points for 2st segment add +1 to that index compared to the next one
        int[] twoThirdsToRight = new int[array.length];
        for (int i = array.length-2; i >= 0; --i)
            if (prefixSum[i] == 2*sumByThree)
                twoThirdsToRight[i] = twoThirdsToRight[i+1]+1;
            else
                twoThirdsToRight[i] = twoThirdsToRight[i+1];

        // Counting ways
        long ways = 0;
        if (thirdsByLeft[0] == 1)
            ways += twoThirdsToRight[1];
        for (int i = 1; i < array.length-2; ++i)
            if (thirdsByLeft[i] != thirdsByLeft[i-1]) // i.e this is a possible endpoint for the first segment
                ways += twoThirdsToRight[i+1];
        return ways;
    }

    // Creates and returns prefix sum for given array
    static long[] getPrefixSum(int[] array) {
        long[] prefixSum = new long[array.length];
        prefixSum[0] = array[0];
        for (int i = 1; i < prefixSum.length; ++i)
            prefixSum[i] = prefixSum[i-1] + array[i];
        return prefixSum;
    }
}
