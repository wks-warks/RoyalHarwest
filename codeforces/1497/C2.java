import java.util.Scanner;

public class CF1497C1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        for (int t = 0; t < tests; t++) {
            int sum = scanner.nextInt();
            int count = scanner.nextInt();
            int[] nums = getNums(sum, count);
            for (var num : nums) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    static int[] getNums(int sum, int count) {
        int[] nums = new int[count];
        for (int i = 3; i < count; i++) {
            nums[i] = 1;
        }
        sum -= count - 3;

        if ((sum & 1) == 1) {
            nums[0] = 1;
            nums[1] = nums[2] = sum >> 1;
        } else {
            if (sum % 4 == 0) {
                nums[0] = sum >> 1;
                nums[1] = nums[2] = nums[0] >> 1;
            } else {
                nums[0] = 2;
                nums[1] = nums[2] = (sum >> 1) - 1;
            }
        }

        return nums;
    }
}