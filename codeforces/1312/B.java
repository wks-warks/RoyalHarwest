//Codeforces 1312B 
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class CF1312B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = SC.nextInt();
            Integer[] arr = new Integer[arrLen];
            for (int i = 0; i < arrLen; ++i)
                arr[i] = SC.nextInt();
            printBogoSort(arr);
        }
    }

    // Computes and prints array in bogosort order
    static void printBogoSort(Integer[] arr) {
        Arrays.sort(arr, Collections.reverseOrder());
        for (int i = 0; i < arr.length; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
