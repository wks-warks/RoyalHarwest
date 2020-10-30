//Codeforces 1234A 
import java.util.Scanner;
import java.util.stream.*;

public class CF1234A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int queries = SC.nextInt();
        for (int q = 0; q < queries; ++q) {
            int goods = SC.nextInt();
            int[] prices = new int[goods];
            for (int g = 0; g < goods; ++g)
                prices[g] = SC.nextInt();
            int priceEach = computeProfitablePrice(goods, prices);
            System.out.println(priceEach);
        }
    }
    // Computes and returns minimum price of each good such that there is no loss compared to original total
    static int computeProfitablePrice(int goods, int[] prices) {
        int sum = IntStream.of(prices).sum();
        return MathLib.LIG(sum, goods);
    }
}

class MathLib {
    // Returns least integer greater than or equal to (double) numerator/denominator
    static int LIG(int numerator, int denominator) {
        return (numerator+denominator-1) / denominator;
    }
}
