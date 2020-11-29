//Codeforces 1228A 
import java.util.Scanner;

public class CF1228A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int left = SC.nextInt();
        int right = SC.nextInt();
        int x = computeX(left, right);
        System.out.println(x);
    }

    // Computes and returns satisfactory x, else if not found, retruns -1
    static int computeX(int left, int right) {
        for (int candidate = left; candidate <= right; ++candidate)
            if (allDistinct(candidate))
                return candidate;
        return -1;
    }

    static boolean allDistinct(int num) {
        int[] digitOccurrence = new int[10];
        while (num > 0) {
            int digit = num % 10;
            digitOccurrence[digit] += 1;
            if (digitOccurrence[digit] == 2)
                return false;
            num /= 10;
        }
        return true;
    }
}
