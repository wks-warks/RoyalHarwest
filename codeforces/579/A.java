// Codeforces 579A
import java.util.Scanner;

public class CF579A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int desiredAmount = SC.nextInt();
        int requiredInput = computeMinInput(desiredAmount);
        System.out.println(requiredInput);
    }

    // Computes and returns the minimum number of bacteria required as input to get the desired amount
    static int computeMinInput(int desiredAmount) {
        return countSetBits(desiredAmount);
    }

    // Reference: https://www.geeksforgeeks.org/count-set-bits-in-an-integer/
    // Returns number of set bits in a number using Brian Kernighan's Algorithm
    static int countSetBits(int num) {
        int setCount = 0;
        while (num > 0) {
            num &= (num-1);
            setCount += 1;
        }
        return setCount;
    }
}