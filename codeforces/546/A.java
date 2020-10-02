// Codeforces 546A
import java.util.Scanner;

public class CF546A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int initCost = SC.nextInt();
        int money = SC.nextInt();
        int bananas = SC.nextInt();
        int borrow = computeAmountToBorrow(initCost, money, bananas);
        System.out.println(borrow); 
    }

    static int computeAmountToBorrow(int initCost, int money, int bananas) {
        int totalCost = bananas * (bananas+1) / 2 * initCost;
        int deficit = Math.max(0, totalCost - money);
        return deficit;
    }
}