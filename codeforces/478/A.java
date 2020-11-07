//Codeforces 478A 
import java.util.Scanner;

public class CF478A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int[] coinsAtEnd = new int[5];
        for (int p = 0; p < 5; ++p)
            coinsAtEnd[p] = SC.nextInt();
        int initialBet = computeBetAmount(coinsAtEnd);
        System.out.println(initialBet);
    }

    // Computes and returns initial bet amount
    static int computeBetAmount(int[] coinsAtEnd) {
        int coinSum = arraySum(coinsAtEnd);
        if (coinSum > 0 && coinSum % 5 == 0) // Feasible amount
            return coinSum/5;
        else
            return -1; // Impossible amount
    }

    // Computes and returns sum of elements of an array
    static int arraySum(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; ++i)
            sum += array[i];
        return sum;
    }
}
