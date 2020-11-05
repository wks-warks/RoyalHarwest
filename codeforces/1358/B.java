//Codeforces 1358B 
import java.util.Scanner;
import java.util.Arrays;

public class CF1358B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int potentialVisitors = SC.nextInt();
            int[] demands = new int[potentialVisitors]; // for each visitor-i, there must be at least demands[i] other people before or with her
            for (int v = 0; v < potentialVisitors; ++v)
                demands[v] = SC.nextInt();
            int maxAttendees = computeMaxAttendees(demands);
            System.out.println(maxAttendees);
        }
    }

    // Computes and returns maximum possible number of attendees
    static int computeMaxAttendees(int[] demands) {
        // We bring the visitors together at once as this easily allows us to compute the optimal value
        Arrays.sort(demands);
        int attendees = 1;
        for (int i = demands.length-1; i >= 0; --i)
            if (demands[i] <= i+1) {
                attendees += i+1;
                break;
            }
        return attendees;
    }
}
