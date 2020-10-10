// Codeforces 581A
import java.util.Scanner;

public class CF581A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int redSocks = SC.nextInt();
        int blueSocks = SC.nextInt();
        int fashionableDays = computeFashionableDays(redSocks, blueSocks);
        int remainingDays = computeRemainingDays(redSocks, blueSocks, fashionableDays);
        System.out.println(fashionableDays + " " + remainingDays);
    }
    
    // Computes number of days when Vasya can form a fashionable pair of socks
    static int computeFashionableDays(int redSocks, int blueSocks) {
        return Math.min(redSocks, blueSocks);
    }

    static int computeRemainingDays(int redSocks, int blueSocks, int fashionableDays) {
        int totalDays = (redSocks+blueSocks) / 2;
        return totalDays-fashionableDays;
    }
}