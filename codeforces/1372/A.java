// Codeforces 1372A
import java.util.Scanner;

public class CF1372A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = SC.nextInt();
            int[] arr = getOnesArr(n); // Gets a positive array of length n
            out(arr);
        }
    }

    // Returns an array of length n, filled with 1s
    static int[] getOnesArr(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i)
            arr[i] = 1;
        return arr;
    }

    // Prints output corresponding to array
    static void out(int[] arr) {
        for (int i = 0; i < arr.length; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}