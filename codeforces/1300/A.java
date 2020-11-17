//Codeforces 1300A 
import java.util.Scanner;
import java.util.stream.IntStream;

public class CF1300A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrSize = SC.nextInt();
            int[] arr = new int[arrSize];
            for (int i = 0; i < arrSize; ++i)
                arr[i] = SC.nextInt();
            int arrSum = IntStream.of(arr).sum();
            int zeroCount = countZeroes(arr);
            if (arrSum + zeroCount == 0)
                System.out.println(zeroCount+1);
            else
                System.out.println(zeroCount);
        }
    }

    // Computes and returns number of zeroes in array
    static int countZeroes(int[] arr) {
        int zeroes = 0;
        for (int i = 0; i < arr.length; ++i)
            if (arr[i] == 0)
                zeroes += 1;
        return zeroes;
    }
}
