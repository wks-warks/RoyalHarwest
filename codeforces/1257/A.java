//Codeforces 1257A 
import java.util.Scanner;

public class CF1257A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int students = SC.nextInt();
            int swaps = SC.nextInt();
            int pos1 = SC.nextInt();
            int pos2 = SC.nextInt();
            int maxDistance = computeMaxDistance(students, swaps, pos1, pos2);
            System.out.println(maxDistance);
        }
    }

    // Computes and returns max distance attainable between students
    static int computeMaxDistance(int students, int swaps, int pos1, int pos2) {
        return Math.min(students-1, Math.abs(pos2-pos1) + swaps);
    }
}
