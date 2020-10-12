// Codeforces 460A
import java.util.Scanner;

public class CF460A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int socks = SC.nextInt();
        int buyingInterval = SC.nextInt();
        int canWearFor = computeDaysWithSocks(socks, buyingInterval, 0); // 0-days covered
        System.out.println(canWearFor);
    }

    // Computes and returns number of days for which Vasya can wear socks
    static int computeDaysWithSocks(int socks, int buyingInterval, int intervalCovered) {
        if (socks == 0)
            return 0;
        else
            return socks + computeDaysWithSocks((socks+intervalCovered)/buyingInterval, buyingInterval, (socks+intervalCovered) % buyingInterval);
    }
}