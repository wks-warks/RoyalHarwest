//Codeforces 1206B 
import java.util.Scanner;

public class CF1206B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numElements = SC.nextInt();
        int[] numbers = new int[numElements];
        for (int n = 0; n < numElements; ++n)
            numbers[n] = SC.nextInt();
        long minCoinsTo1 = computeMinCoinsTo1(numElements, numbers);
        System.out.println(minCoinsTo1);
    }

    // Computes and returns number of coins to be paid (minimum) in order to get 1 as the product
    static long computeMinCoinsTo1(int numElements, int[] numbers) {
        long totalCost = 0;
        int zeroes = 0;
        int negatives = 0;
        for (int i = 0; i < numElements; ++i) {
            if (numbers[i] < 0) {
                negatives += 1;
                totalCost += -1 - numbers[i];
            }
            else if (numbers[i] == 0) {
                totalCost += 1;
                zeroes += 1;
            }
            else {
                totalCost += numbers[i] - 1;
            }
        }
        if (negatives % 2 != 0 && zeroes == 0)
            totalCost += 2;
        return totalCost;      
    }
}
