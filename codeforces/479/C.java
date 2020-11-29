//Codeforces 479C 
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class CF479C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int exams = SC.nextInt();
        int[][] examDates = new int[exams][2]; // 0-actual, 1-allowed
        for (int e = 0; e < exams; ++e) {
            examDates[e][0] = SC.nextInt();
            examDates[e][1] = SC.nextInt();
        }
        int earliestFinish = computeEarliestFinish(examDates);
        System.out.println(earliestFinish);
    }

    // Computes and returns earliest finish date satisfying conditions
    static int computeEarliestFinish(int[][] examDates) {
        Arrays.sort(examDates, (a, b) -> (a[0] - b[0] == 0) ? a[1] - b[1] : a[0] - b[0]);
        int lastExamGiven = 0;
        for (int e = 0; e < examDates.length; ++e) {
            int givingDate = examDates[e][1] >= lastExamGiven ? examDates[e][1] : examDates[e][0];
            lastExamGiven = givingDate;
        }
        return lastExamGiven;
    }
}
