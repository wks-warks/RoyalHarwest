//Codeforces 1338A 
import java.util.Scanner;

public class CF1338A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = SC.nextInt();
            int[] arr = new int[arrLen];
            for (int i = 0; i < arrLen; ++i) {
                arr[i] = SC.nextInt();
            }
            int operatingTime = computeOperatingTime(arr);
            System.out.println(operatingTime);
        }
    }

    // Computes and returns operating time
    static int computeOperatingTime(int[] arr) {
        int operatingTime = 0;
        int prevLargest = arr[0];

        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] >= prevLargest) {
                prevLargest = arr[i];
                continue;
            }
            int operatinsRequired = largestSetBitPosition(prevLargest - arr[i]);
            operatingTime = Math.max(operatingTime, operatinsRequired);
        }

        return operatingTime;
    }

    // Computes and returns largest set bit position (1 - indexed, from right)
    static int largestSetBitPosition(int n) {
        int msbPosition = 30;
        while ((n & (1 << msbPosition)) == 0) {
            msbPosition -= 1;
        }
        return msbPosition + 1;
    }
}
