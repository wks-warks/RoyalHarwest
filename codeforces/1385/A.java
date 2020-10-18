// Codeforces 1385A
import java.util.Scanner;
import java.util.Arrays;
import java.util.TreeMap;

public class CF1385A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int[] nums = new int[3];
            for (int i = 0; i < 3; ++i)
                nums[i] = SC.nextInt();
            checkPossibility(nums);
        }
    }

    // Checks and Prints whether pairwise maximum representation is possible
    static void checkPossibility(int[] nums) {
        TreeMap<Integer, Integer> numCount = new TreeMap<>(); // Counts occurrences of numbers
        for (int i = 0; i < nums.length; ++i)
            if (numCount.containsKey(nums[i]))
                numCount.put(nums[i], numCount.get(nums[i]) + 1);
            else
                numCount.put(nums[i], 1);
        
        if ((numCount.size() == 3) || (numCount.size() == 2 && numCount.get(numCount.firstKey()) == 2)) {
            out(false, nums);            
        }
        else {
            if (numCount.size() == 1)
                out(true, nums);
            else {
                Arrays.sort(nums);
                nums[2] = 1;
                out(true, nums);
            }
        }
    }

    // Prints output based on condition
    static void out(boolean condition, int[] nums) {
        if (condition) {
            System.out.println("YES");
            for (int i = 0; i < nums.length; ++i)
                System.out.print(nums[i] + " ");
            System.out.println();
        }
        else
            System.out.println("NO");
    }
}