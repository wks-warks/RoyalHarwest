//Codeforces 279B 
import java.util.Scanner;

public class CF279B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int books = SC.nextInt();
        int time = SC.nextInt();
        int[] duration = new int[books]; // Time needed to read each book
        for (int b = 0; b < books; ++b)
            duration[b] = SC.nextInt();
        int booksRead = computeBooksRead(books, time, duration);
        System.out.println(booksRead);
    }

    // Computes and returns maximum no. of books Valera can read
    static int computeBooksRead(int books, int timeLimit, int[] duration) {
        int[] prefixSum = getPrefixSum(duration);
        int booksRead = 0;
        
        // Finding best value for every index
        for (int i = 0; i < prefixSum.length; ++i) {
            int timeBefore = (i > 0 ? prefixSum[i-1] : 0); // Time before book-i (0-idxing)
            int timeConsumed = prefixSum[i] - timeBefore; // Time for book-i (0-idxing)
            if (timeConsumed > timeLimit)
                continue;
            int readTillIdx = i; // Can read upto this idx
            // Performing Binary Search to find largest index that allows a time <= threshold
            int left, mid, right;
            left = i+1;
            right = prefixSum.length-1;
            while (left <= right) {
                mid = (left+right) / 2;
                if (prefixSum[mid] - timeBefore <= timeLimit) {
                    readTillIdx = mid;
                    left = mid+1;
                }
                else
                    right = mid-1;
            }
            booksRead = Math.max(booksRead, readTillIdx-i+1);
        }

        return booksRead;
    }

    // Computes and returns prefixSum of array passed
    static int[] getPrefixSum(int[] array) {
        int[] prefixSum = new int[array.length];
        prefixSum[0] = array[0];
        for (int i = 1; i < prefixSum.length; ++i)
            prefixSum[i] = prefixSum[i-1] + array[i];
        return prefixSum;
    }
}