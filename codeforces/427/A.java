// Codeforces 427A
import java.util.Scanner;

public class CF427A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numEvents = SC.nextInt(); // Number of events
        int[] events = new int[numEvents];
        for (int i = 0; i < numEvents; ++i)
            events[i] = SC.nextInt();
        int untreatedCrimes = computeUntreated(events);
        System.out.println(untreatedCrimes);
    }

    // Computes and returns number of crimes that will go untreated
    static int computeUntreated(int[] events) {
        int policeStrength = 0;
        int untreated = 0;
        for (int i = 0; i < events.length; ++i)
            if (events[i] == -1) {
                if (policeStrength == 0)
                    untreated += 1;
                policeStrength = Math.max(policeStrength-1, 0);
            }
            else
                policeStrength += events[i];
        return untreated;
    }
}