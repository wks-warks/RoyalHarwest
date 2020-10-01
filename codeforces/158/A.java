// Codeforces 158A
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class CF158A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int participants = SC.nextInt();
        int threshold = SC.nextInt();
        Integer[] scores = new Integer[participants]; // needs to be Integer[], since Collections.reverseOrder() is to be used
        for (int p = 0; p < participants; ++p)
            scores[p] = SC.nextInt();

        int advancingParticipants = computeAdvancingParticipants(scores, threshold);
        System.out.println(advancingParticipants);
    }

    // Computes number of participants who advance to the next round
    static int computeAdvancingParticipants(Integer[] scores, int threshold) {
        Integer[] sortedScores = scores.clone();
        Arrays.sort(sortedScores, Collections.reverseOrder());
        int advancingParticipants = 0;
        int thresholdScore = Math.max(1, sortedScores[threshold-1]);

        for (int p = 0; p < scores.length; ++p)
            if (sortedScores[p] >= thresholdScore)
                advancingParticipants += 1;
        return advancingParticipants;
    }
}