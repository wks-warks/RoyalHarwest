// Codeforces 318A
import java.util.Scanner;

public class CF318A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        long numbers = SC.nextLong();
        long atPos = SC.nextLong();
        long numAtPos = getNumAtPos(numbers, atPos);
        System.out.println(numAtPos);
    }

    // Gets number at position atPos after arranging all odd numbers first and then even numbers
    static long getNumAtPos(long numbers, long atPos) {
        long evenNums = numbers / 2;
        long oddNums = numbers - evenNums;
        if (atPos <= oddNums)
            return 2 * atPos - 1;
        else
            return 2 * (atPos - oddNums);
    }
}