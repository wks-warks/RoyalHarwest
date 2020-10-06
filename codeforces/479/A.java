// Codeforces 479A - variant
import java.util.Scanner;

public class CF479Avariant {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int[] nums = new int[3];
        for (int i = 0; i < 3; ++i)
            nums[i] = SC.nextInt();
        int result = getMaxResult(nums);
        System.out.println(result);
    }

    // Gets maximum result from given numbers
    static int getMaxResult(int[] nums) {
        int[] resultSpace = {
            nums[0] + nums[1] + nums[2],
            nums[0] + nums[1] * nums[2],
            nums[0] * nums[1] + nums[2],
            nums[0] * nums[1] * nums[2],
            (nums[0]+nums[1]) * nums[2],
            nums[0] * (nums[1]+nums[2])
        };
        int result = 0;
        for (int r = 0; r < resultSpace.length; ++r)
            result = Math.max(result, resultSpace[r]);
        return result;
    }
}