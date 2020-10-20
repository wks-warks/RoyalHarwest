// Codeforces 556A
import java.util.Scanner;

public class CF556A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int strLen = SC.nextInt();
        String numStr = SC.next();
        int minLengthAfter = lengthAfterOperating(numStr);
        System.out.println(minLengthAfter);
    }

    // Computes and returns minimum length of string after performing operations
    static int lengthAfterOperating(String numStr) {
        int ones = 0;
        int zeroes = 0;
        // Answer = Math.max(ones, zeroes) - Math.min(ones, zeroes), as as long as there exist some 0s and some 1s there must exist one pair of adjacent 0 and 1
        for (int i = 0; i < numStr.length(); ++i)
            if (numStr.charAt(i) == '0')
                zeroes += 1;
            else
                ones += 1;
        return Math.max(zeroes, ones) - Math.min(zeroes, ones);
    }
}