// Codeforces 25A
import java.util.Scanner;

public class CF25A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numCount = SC.nextInt();
        int[] nums = new int[numCount];
        for (int i = 0; i < numCount; ++i)
            nums[i] = SC.nextInt();
        int differentElementIdx = getDiffIdx(nums);
        System.out.println(differentElementIdx);
    }

    // Finds and returns 1-based idx of the odd-one-out element
    static int getDiffIdx(int[] nums) {
        int oddCount = 0;
        int oddIdx = -1;
        int evenCount = 0;
        int evenIdx = -1;
        for (int i = 0; i < nums.length; ++i)
            if (nums[i] % 2 == 0) {
                evenCount += 1;
                evenIdx = i+1;
            }
            else {
                oddCount += 1;
                oddIdx = i+1;
            }
        
        if (oddCount > evenCount)
            return evenIdx;
        else
            return oddIdx;
    }
}