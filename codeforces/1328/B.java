//Codeforces 1328B 
import java.util.Scanner;

public class CF1328B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int letters = SC.nextInt();
            long wordIdx = SC.nextLong(); // Word to be found (its index in the dictionary)
            String word = getWord(letters, wordIdx);
            solution.append(word + "\n");
        }
        System.out.print(solution.toString());
    }

    // Computes and returns word ranked same as provided argument in a dictionary of words with given length, composed of 2 b's and all other letters as a
    static String getWord(int letters, long wordIdx) {
        long totalWords = sumUpto(letters);
        
        // Performing binary search over appropriate indices for the left-ward occurring b and finding the corresponding righ-ward occurrence once left-ward is finalized
        int left = 1;
        int right = letters-1;
        while (left <= right) {
            int mid = (left + right) / 2;
            long wordsBefore = sumUpto(letters - (mid+1));
            if (wordsBefore >= wordIdx) {
                left = mid + 1;
            }
            else if (wordsBefore + letters - mid < wordIdx) {
                right = mid - 1;
            }
            else {
                int rightBIdx = letters - (int) (wordIdx - wordsBefore) + 1;
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i <= letters; ++i) {
                    if (i == mid || i == rightBIdx)
                        sb.append('b');
                    else
                        sb.append('a');
                }
                return sb.toString();
            }
        }
        return null;
    }

    // Computes and returns the sum of integers from 1 to n
    static long sumUpto(int n) {
        return (long)n * (n+1) / 2;
    }
}
