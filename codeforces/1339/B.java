//Codeforces 1339B 
import java.util.Scanner;
import java.util.Arrays;

public class CF1339B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = SC.nextInt();
            int[] arr = new int[arrLen];
            for (int i = 0; i < arrLen; ++i)
                arr[i] = SC.nextInt();
            printSortedDiffArray(arr);
        }
    }

    // Computes and prints sorted difference array
    static void printSortedDiffArray(int[] arr) {
        Arrays.sort(arr);
        int[] sortedDiffArray = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            if (i % 2 == 0)
                sortedDiffArray[i] = arr[i/2];
            else
                sortedDiffArray[i] = arr[arr.length - i/2 - 1];
        }
        for (int i = sortedDiffArray.length-1; i >= 0; --i)
            System.out.print(sortedDiffArray[i] + " ");
        System.out.println();
    }
}
