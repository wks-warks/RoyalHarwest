// Codeforces 1385B
import java.util.Scanner;
import java.util.HashMap;

public class CF1385B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int permLen = SC.nextInt(); // Length of permutation
            int arrLen = 2 * permLen;
            int[] arr = new int[arrLen];
            for (int i = 0; i < arrLen; ++i)
                arr[i] = SC.nextInt();
            int[] permutation = getPermutation(arr);
            out (permutation);
        }
    }

    // Computes and returns the original permutation
    static int[] getPermutation(int[] arr) {
        HashMap<Integer, Boolean> elementsFound = new HashMap<>();
        int[] permutation = new int[arr.length/2];
        int permIdx = 0; // Index of element to be added 
        for (int i = 0; i < arr.length; ++i)
            if (!elementsFound.containsKey(arr[i])) {
                permutation[permIdx++] = arr[i];
                elementsFound.put(arr[i], true);
            }
        return permutation;
    }

    // Prints output corresponding to the array passed
    static void out(int[] arr) {
        for (int i = 0; i < arr.length; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}