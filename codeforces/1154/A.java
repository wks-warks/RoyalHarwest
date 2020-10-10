// Codeforces 1154A
import java.util.Scanner;
import java.util.Arrays;

public class CF1154A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        long[] inputs = new long[4]; // a+b, a+c, c+b, a+b+c in some order where a,b,c > 0
        for (int i = 0; i < 4; ++i)
            inputs[i] = SC.nextLong();
        int[] guess = guessNumbers(inputs);
        out(guess);
    }

    // Derives and returns an array of three numbers from the input provided
    static int[] guessNumbers(long[] inputs) {
        int[] guess = new int[3];
        Arrays.sort(inputs);
        for (int i = 0; i < 3; ++i)
            guess[i] = (int)(inputs[3] - inputs[i]);
        return guess;
    }

    // Prlongs output for the given array
    static void out(int[] arr) {
        for (int i = 0; i < arr.length; ++i)
            System.out.print(arr[i] + " ");
    }
}