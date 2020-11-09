//Codeforces 1354A 
import java.util.Scanner;

public class CF1354A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int sleepRequired = SC.nextInt();
            int alarmTime = SC.nextInt();
            int snoozeInterval = SC.nextInt();
            int timeUntilSleep = SC.nextInt(); // Time taken to fall asleep
            long timeInBed = computeTimeInBed(sleepRequired, alarmTime, snoozeInterval, timeUntilSleep);
            System.out.println(timeInBed);
        }
    }

    // Computes and returns time spent in bed. Returns -1 if he never gets out.
    static long computeTimeInBed(int sleepRequired, int alarmTime, int snoozeInterval, int timeUntilSleep) {
        long timeInBed = alarmTime;
        sleepRequired -= timeInBed;
        if (sleepRequired <= 0)
            return timeInBed;

        if (snoozeInterval <= timeUntilSleep)
            return -1; // Impossible to fall asleep

        timeInBed += (long)LIG(sleepRequired, snoozeInterval-timeUntilSleep)*snoozeInterval;
        return timeInBed;
    }

    // Computes and returns the Least Integer Greater than or equal to (double) numerator/denominator
    static int LIG(int numerator, int denominator) {
        return (numerator+denominator-1)/denominator;
    }
}
