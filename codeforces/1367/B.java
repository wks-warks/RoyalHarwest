// Codeforces 1367B
import java.util.Scanner;

public class CF1367B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = SC.nextInt();
            int[] arr = new int[arrLen];
            for (int i = 0; i < arrLen; ++i)
                arr[i] = SC.nextInt();
            int movesToDesirable = computeMovesToDesirable(arr);
            System.out.println(movesToDesirable);
        }
    }

    // Computes and returns number of moves required to make the array good, returns -1 if impossible
    static int computeMovesToDesirable(int[] arr) {
        int evenNotInPlace = 0; // Number of even elements not in place
        int oddNotInPlace = 0; // Number of odd elements not in place
        int inPlace = 0; // Number of elements already in correct positions
        for (int i = 0; i < arr.length; ++i) {
            int iParity = i % 2; // 0 -> even, 1 -> odd
            int elementParity = arr[i] % 2;
            if (iParity == elementParity)
                inPlace += 1;
            else if (elementParity == 0)
                evenNotInPlace += 1;
            else
                oddNotInPlace += 1;
        }
        if (evenNotInPlace != oddNotInPlace) // If true => Impossible to make the array good
            return -1;
        else
            return (arr.length - inPlace)/2; // Number of swaps required
    }
}