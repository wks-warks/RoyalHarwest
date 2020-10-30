//Codeforces 734B 
import java.util.Scanner;

public class CF734B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int twos = SC.nextInt();
        int threes = SC.nextInt();
        int fives = SC.nextInt();
        int sixes = SC.nextInt();
        int maxSum = computeMaxSum(twos, threes, fives, sixes); // Max sum of numbers formed by Anton
        System.out.println(maxSum);
    }

    // Computes and returns max. sum of numbers formed by Anton
    static int computeMaxSum(int twos, int threes, int fives, int sixes) {
        int count256s = Math.min(twos, Math.min(fives, sixes));
        twos -= count256s;
        int count32s = Math.min(twos, threes);
        return 256*count256s + 32*count32s;
    }
}
