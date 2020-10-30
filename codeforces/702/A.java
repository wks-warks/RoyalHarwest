//Codeforces 702A 
import java.util.Scanner;

public class CF702A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int elements = SC.nextInt();
        int[] arr = new int[elements];
        for (int i = 0; i < arr.length; ++i)
            arr[i] = SC.nextInt();
        int maxIncLen = computeMaxIncLen(arr);
        System.out.println(maxIncLen);
    }

    // Computes and returns maximum length of strictly increasing subarray
    static int computeMaxIncLen(int[] arr) {
        int len, maxLen;
        maxLen = len = 1;
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] > arr[i-1])
                len += 1;
            else
                len = 1;
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }
}
