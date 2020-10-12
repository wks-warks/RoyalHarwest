// Codeforces 313A
import java.util.Scanner;

public class CF313A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int balance = SC.nextInt();
        int newBalance = getBestBalance(balance);
        System.out.println(newBalance);
    }

    // Computes and returns best balance possible for Ilya
    static int getBestBalance(int balance) {
        // Could work with strings and parsing but I prefer the following method-
        int possibleBal1 = balance/10;
        int possibleBal2 = (balance - balance%100)/10 + balance%10;
        return Math.max(balance, Math.max(possibleBal1, possibleBal2));
    }
}