//Codeforces 768A 
import java.util.Scanner;
import java.util.Arrays;
import java.util.IntSummaryStatistics;

public class CF768A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int stewards = SC.nextInt();
        int[] strengths = new int[stewards];
        for (int s = 0; s < stewards; ++s)
            strengths[s] = SC.nextInt();
        int stewardsFed = countStewardsFed(strengths);
        System.out.println(stewardsFed);
    }

    // Computes and returns the number of stewards fed
    static int countStewardsFed(int[] strengths) {
        IntSummaryStatistics stat = Arrays.stream(strengths).summaryStatistics();
        int min = stat.getMin();
        int max = stat.getMax();
        int fed = 0;
        for (int strn : strengths)
            if (min < strn && strn < max)
                fed += 1;
        
        return fed;
    }
}
