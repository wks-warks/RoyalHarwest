//Codeforces 1355B 
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;

public class CF1355B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int explorers = SC.nextInt();
            int[] inexperience = new int[explorers];
            for (int e = 0; e < explorers; ++e)
                inexperience[e] = SC.nextInt();
            int maxGroups = computeMaxGroups(explorers, inexperience);
            solution.append(maxGroups + "\n");
        }
        System.out.print(solution.toString());
    }

    // Computes and returns the maximum number of groups that can be formed
    static int computeMaxGroups(int explorers, int[] inexperience) {
        TreeMap<Integer, Integer> inexperienceDistribution = new TreeMap<>();
        for (int e = 0; e < explorers; ++e)
            if (!inexperienceDistribution.containsKey(inexperience[e])) {
                inexperienceDistribution.put(inexperience[e], 1);
            }
            else {
                int presCount = inexperienceDistribution.get(inexperience[e]);
                inexperienceDistribution.put(inexperience[e], presCount + 1);
            }
        
        int groups = 0;
        int extraPeople = 0;
        for (Map.Entry<Integer, Integer> me : inexperienceDistribution.entrySet()) {
            int requiredGroupSize = me.getKey();
            int people = me.getValue();
            groups += (people + extraPeople) / requiredGroupSize;
            extraPeople = (people + extraPeople) % requiredGroupSize;
        }
        return groups;
    }
}
