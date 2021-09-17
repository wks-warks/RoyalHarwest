import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class CF1006C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int arrSz = sc.nextInt();
        int[] arr = new int[arrSz];
        for (int i = 0; i < arrSz; i++) {
            arr[i] = sc.nextInt();
        }

        long maxCommonSum = getMaxCommonSum(arr);
        System.out.println(maxCommonSum);
    }

    static long getMaxCommonSum(int[] arr) {
        Map<Long, Integer> leftSum = new HashMap<Long, Integer>();
        Map<Long, Integer> rightSum = new HashMap<Long, Integer>();
        leftSum.put(0L, -1);
        rightSum.put(0L, arr.length);


        long prefixSum = 0L;
        long suffixSum = 0L;
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            suffixSum += arr[arr.length-1-i];
            
            leftSum.put(prefixSum, i);
            rightSum.put(suffixSum, arr.length-1-i);
        }

        long maxSum = 0L;
        for (var sum : leftSum.keySet()) {
            if (rightSum.containsKey(sum) && rightSum.get(sum) > leftSum.get(sum)) {
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }
}