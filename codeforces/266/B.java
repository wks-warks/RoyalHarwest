// Codeforces 266B
import java.util.Scanner;

public class CF266B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numChildren = SC.nextInt(); // Number of children
        int t = SC.nextInt();
        String queue = SC.next();
        String finalArrangement = getFinalArrangement(queue, t);
        System.out.println(finalArrangement);
    }

    // Gets final arrangement of students in queue after 't' seconds
    static String getFinalArrangement(String queueStr, int t) {
        char[] queue = queueStr.toCharArray();
        while (t > 0) {
            t--;
            for (int st = 1; st < queue.length; ++st) {
                boolean swappable = queue[st] == 'G' && queue[st-1] == 'B';
                if (swappable) {
                    // swap
                    queue[st] = 'B';
                    queue[st-1] = 'G';
                    st += 1; // Ignore the person next in line to avoid multiple swaps for one person
                }
            }
        }
        return String.valueOf(queue);

    }
}