//Codeforces 1296B
import java.util.Scanner;

public class CF1296B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int initialMoney = SC.nextInt();
            int moneySpent = computeMaxMoneySpent(initialMoney);
            System.out.println(moneySpent);
        }
    }

    // Computes and returns maximum money spent
    static int computeMaxMoneySpent(int money) {
        if (money < 10)
            return money;
        else
            return money - money%10 + computeMaxMoneySpent(money/10 + money % 10);
    }
}