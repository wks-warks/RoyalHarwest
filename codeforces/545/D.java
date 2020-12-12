//Codeforces 545D 
import java.util.Scanner;
import java.util.Arrays;

public class CF545D {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int people = SC.nextInt();
        int[] dissatisfactionPoints = new int[people];
        for (int p = 0; p < people; ++p)
            dissatisfactionPoints[p] = SC.nextInt();
        Arrays.sort(dissatisfactionPoints);
        int maximumSatisfied = countMaximumSatisfied(dissatisfactionPoints);
        System.out.println(maximumSatisfied);
    }

    // Computes and returns maximum number of satisfied people
    static int countMaximumSatisfied(int[] dissatisfactionPoints) {
        int time = dissatisfactionPoints[0];
        int satisfied = 1;
        for (int i = 1; i < dissatisfactionPoints.length; ++i) {
            if (time > dissatisfactionPoints[i])
                continue;
            else {
                time += dissatisfactionPoints[i];
                satisfied += 1;
            }
        }
        return satisfied;
    }
}
