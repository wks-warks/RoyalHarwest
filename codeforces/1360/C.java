//Codeforces 1360C 
import java.util.Scanner;

public class CF1360C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = SC.nextInt();
            int[] arr = new int[arrLen];
            for (int i = 0; i < arrLen; ++i)
                arr[i] = SC.nextInt();
            boolean partitionExists = checkPartitionExistence(arr);
            out(partitionExists);
        }
    }

    // Checks and returns whether or not a partition satisfying constraints exists
    static boolean checkPartitionExistence(int[] arr) {
        int odd = 0;
        int even = 0;
        int[] count = new int[100]; // Keeps count of elements
        for (int i = 0; i < arr.length; ++i) {
            if ((arr[i] & 1) == 0)
                even += 1;
            else
                odd += 1;
            count[arr[i]-1] += 1; 
        }
        if ((odd&1) == 0)
            return true;
        int adjacentPairs = 0; // Could've just checked for existence but I wanted to count
        for (int i = 1; i < 100; ++i) {
            int pickElements = Math.min(count[i], count[i-1]);
            adjacentPairs += pickElements;
            count[i] -= pickElements;
        }
        if (adjacentPairs > 0)
            return true;
        else
            return false;
    }


    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
