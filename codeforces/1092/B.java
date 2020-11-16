//Codeforces 1092B 
import java.util.Scanner;
import java.util.Arrays;

public class CF1092B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int students = SC.nextInt();
        int[] skills = new int[students];
        for (int s = 0; s < students; ++s)
            skills[s] = SC.nextInt();
        int problemsToSolve = countProblemsToSolve(skills);
        System.out.println(problemsToSolve);
    }

    // Computes and returns the MINIMUM number of problems to solve to get the students paired
    static int countProblemsToSolve(int[] skills) {
        // We follow a greedy approach, getting the students paired from the top(most skilled ones)
        Arrays.sort(skills);
        int toSolve = 0; // Problems to solve
        for (int i = skills.length-1; i >= 0; i -= 2)
            toSolve += skills[i] - skills[i-1];
        return toSolve;
    }
}
