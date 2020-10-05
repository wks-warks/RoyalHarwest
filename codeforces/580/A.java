// Codeforces 580A
import java.util.Scanner;

public class CF580A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numDays = SC.nextInt();
        int[] earnings = new int[numDays];
        for (int d = 0; d < numDays; ++d)
            earnings[d] = SC.nextInt();
        int maxLength = lengthOfLongestNonDecreasing(earnings);
        System.out.println(maxLength);
    }

    // Computes and returns length of maximum non-decreasing subsegment
    static int lengthOfLongestNonDecreasing(int[] arr) {
        int maxLength = 1; // Length of longest non-decreasing subsegment
        int curLength = 1; // Length of current subsegment in consideration
        int curNumber = arr[0];

        for (int a = 1; a < arr.length; ++a) {
            int prevNumber = curNumber;
            curNumber = arr[a];
            boolean consistent = curNumber >= prevNumber; // Does the current number stay consistent with the ongoing subsegment?
            if (consistent) {
                curLength += 1;
                maxLength = Math.max(maxLength, curLength);
            }
            else
                curLength = 1;
        }
        return maxLength;
    }
}