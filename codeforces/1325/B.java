// Codeforces 1325B
import java.util.Scanner;
import java.util.HashMap;

public class CF1325B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = SC.nextInt();
            int[] arr = new int[arrLen];
            for (int i = 0; i < arr.length; ++i)
                arr[i] = SC.nextInt();
            int maxIncSubLen = countDistinctNumbers(arr); // Here, answer = Number of distinct digits, as we can choose to pick the smallest one in the first instance, and increasingly larger ones thereafter
            System.out.println(maxIncSubLen);
        }
    }

    // Counts and returns number of distinct elements in the array
    static int countDistinctNumbers(int[] arr) {
        HashMap<Integer, Integer> occurrences = new HashMap<>();
        for (int i = 0; i < arr.length; ++i)
            occurrences.put(arr[i], 1);
        return occurrences.size();
    }
}