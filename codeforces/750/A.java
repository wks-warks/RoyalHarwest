// Codeforces 750A
import java.util.Scanner;

public class CF750A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int problems = SC.nextInt(); // i-th problem takes 5*i minutes
        int commuteTime = SC.nextInt(); // Time to reach venue
        int problemsSolvable = computeProblemsSolvable(problems, commuteTime);
        System.out.println(problemsSolvable);
    }

    // Compute number of problems that can be solved before going to the party
    static int computeProblemsSolvable(int problems, int commuteTime) {
        int totalTime = 240; // From 20:00 to midnight
        int remainingTime = totalTime - commuteTime;
        /*
            solvable = maximum n such that
            5*n*(n+1)/2 <= remainingTime
            5/2*(n^2 + n) <= remainingTime
            solvable = (int) n
            where n = Solution of: 5/2*n^2 + 5/2*n - remainingTime = 0
        */
        int solvable = (int)solveQuadratic((double)5/2, (double)5/2, -remainingTime);
        return Math.min(solvable, problems);
    }

    // Finds larger real solution of quadratic
    static double solveQuadratic(double a, double b, double c) {
        double discriminant = b*b - 4*a*c;
        boolean realRoots = discriminant >= 0;
        if (!realRoots)
            return Double.NaN;
        double largerRoot = (-b + Math.sqrt(discriminant))/ (2*a);
        return largerRoot;
    }

}