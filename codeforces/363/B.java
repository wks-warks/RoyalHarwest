//Codeforces 363B 
import java.util.Scanner;

public class CF363B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int planks = SC.nextInt(); // Number of planks
        int pianoWidth = SC.nextInt();
        int[] plankHeights = new int[planks];
        for (int i = 0; i < planks; ++i)
            plankHeights[i] = SC.nextInt();
        int bestPoint = getBestPoint(plankHeights, pianoWidth);
        System.out.println(bestPoint);
    }

    // Computes and returns the best point to initiate removal of planks for minimal removal
    static int getBestPoint(int[] plankHeights, int pianoWidth) {
        int[] heightPrefixSum = getPrefixSum(plankHeights);
        int bestPoint = 1;
        int leastRemoval = heightPrefixSum[pianoWidth-1];
        for (int i = 1; i < heightPrefixSum.length-pianoWidth+1; ++i) {
            int removal = heightPrefixSum[i+pianoWidth-1] - heightPrefixSum[i-1];
            if (removal < leastRemoval) {
                leastRemoval = removal;
                bestPoint = i+1; // 1-indexing
            }
        }
        return bestPoint;
    }

    // Computes and returns prefix sum of array
    static int[] getPrefixSum(int[] array) {
        int[] prefixSum = new int[array.length];
        prefixSum[0] = array[0];
        for (int i = 1; i < prefixSum.length; ++i)
            prefixSum[i] = prefixSum[i-1] + array[i];
        return prefixSum;
    }
}
