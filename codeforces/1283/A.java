// Codeforces 1283A
import java.util.Scanner;

public class CF1283A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int hour = SC.nextInt();
            int minute = SC.nextInt();
            int timeRemaining = computeTimeRemaining(hour, minute);
            System.out.println(timeRemaining);
        }
    }

    // Computes and returns number of minutes remaining for New Year
    static int computeTimeRemaining(int hour, int minute) {
        return (23-hour)*60 + (60-minute);
    }
}