//Codeforces 1249A 
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;

public class CF1249A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int students = SC.nextInt();
            int[] skills = new int[students];
            for (int s = 0; s < students; ++s)
                skills[s] = SC.nextInt();
            int minTeams = computeMinTeamsFormed(skills);
            System.out.println(minTeams);
        }
    }

    // Computes and returns minimum number of teams required
    static int computeMinTeamsFormed(int[] skills) {
        return approach1(skills);
        // return approach2(skills);
    }

    // Approach-1: Sorting
    static int approach1(int[] skills) {
        Arrays.sort(skills);
        int teams = 1;
        int prev = skills[0];
        for (int i = 1; i < skills.length; ++i)
            if (skills[i] == prev + 1)
                return 2; // Found adjacent pair
            else
                prev = skills[i];
        return 1; // Didn't find an adjacent pair of integers
    }

    // Approach-2: Using maps
    static int approach2(int[] skills) {
        HashMap<Integer, Boolean> hasNum = new HashMap<>();
        for (int i = 0; i < skills.length; ++i)
            hasNum.put(skills[i], true);
        
        int teams = 1;
        for (int i = 0; i < skills.length; ++i)
            if (hasNum.containsKey(skills[i]-1))
                return 2; // Found adjacent pair
        return 1; // Didn't find an adjacent pair of integers
    }
}