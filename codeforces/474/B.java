// Codeforces 474B
import java.util.Scanner;

public class CF474B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int wormPiles = SC.nextInt();
        int[] wormsInPiles = new int[wormPiles];
        for (int p = 0; p < wormPiles; ++p)
            wormsInPiles[p] = SC.nextInt();
        int juicyWorms = SC.nextInt(); // Number of juicy worms
        int[] juicyIndex = new int[juicyWorms];   
        for (int j = 0; j < juicyWorms; ++j)
            juicyIndex[j] = SC.nextInt();
        int[] foundInPile = getPileNumbers(wormsInPiles, juicyIndex); // Notes pile(s) in which juicy worm(s) is/are found
        out(foundInPile);
    }

    // Computes and returns array with answer-pileNums
    static int[] getPileNumbers(int[] wormPiles, int[] juicyIndex) {
        int[] wormsUptoPiles = getPrefixSum(wormPiles);
        int[] foundInPile = new int[juicyIndex.length];
        for (int i = 0; i < juicyIndex.length; ++i)
            foundInPile[i] = getPileIndex(juicyIndex[i], wormsUptoPiles);
        return foundInPile;
    }

    // Prints output corresponding to array
    static void out(int[] array) {
        for (int i = 0; i < array.length; ++i)
            System.out.println(array[i]);
    }

    // Returns prefix sum for given array
    static int[] getPrefixSum(int[] array) {
        int[] prefixSum = new int[array.length];
        prefixSum[0] = array[0];
        for (int i = 1; i < prefixSum.length; ++i)
            prefixSum[i] = array[i] + prefixSum[i-1];
        return prefixSum;
    }

    // Computes and returns index(1-based) of pile in which given worm is found based on prefixSum array
    static int getPileIndex(int wormIndex, int[] wormsUptoPiles) {
        if (wormIndex <= wormsUptoPiles[0])
            return 1;
        
        int left = 1;
        int right = wormsUptoPiles.length-1;
        // Performing binary search
        while (true) { // No need to check as we know that we shall return from within
            int mid = (left+right) / 2;
            if (wormsUptoPiles[mid] >= wormIndex) {
                if (wormsUptoPiles[mid-1] >= wormIndex)
                    right = mid-1;
                else
                    return mid+1;
            }
            else {
                left = mid+1;
            }
        }
    }
}