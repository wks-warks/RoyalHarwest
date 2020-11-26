//Codeforces 1399C 
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;

public class CF1399C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int participants = SC.nextInt();
            int[] weight = new int[participants];
            for (int p = 0; p < participants; ++p)
                weight[p] = SC.nextInt();
            int teams = computeTeams(participants, weight);
            solution.append(teams + "\n");
        }
        System.out.print(solution.toString());
    }

    // Computes and returns number of teams (maximum) that can be formed
    static int computeTeams(int participants, int[] weight) {
        // Setting weight map for the count of different weights
        TreeMap<Integer, Integer> weightMap = new TreeMap<>();
        for (int p = 0; p < participants; ++p) {
            if (weightMap.containsKey(weight[p])) {
                weightMap.put(weight[p], weightMap.get(weight[p]) + 1);
            }
            else {
                weightMap.put(weight[p], 1);
            }
        }

        // Getting all possible teams' counts
        TreeMap<Integer, Integer> teamCount = new TreeMap<>();
        for (Map.Entry<Integer, Integer> me : weightMap.entrySet()) {
            int w = me.getKey(); // weight
            int c = me.getValue(); // count
            for (Map.Entry<Integer, Integer> me2 : weightMap.entrySet()) {
                int w2 = me2.getKey();
                int c2 = me2.getValue();
                if (w2 < w)
                    continue;
                else if (w2 == w) {
                    if (teamCount.containsKey(w * 2))
                        teamCount.put(w * 2, teamCount.get(w * 2) + c / 2);
                    else
                        teamCount.put(w * 2, c / 2);
                }
                else {
                    if (teamCount.containsKey(w + w2)) {
                        teamCount.put(w + w2, teamCount.get(w + w2) + Math.min(c, c2));
                    }
                    else {
                        teamCount.put(w + w2, Math.min(c, c2));
                    }
                }
            }
        }

        // Finding maximum possible amount of teams possible
        int maxTeams = 0;
        for (Map.Entry<Integer, Integer> me : teamCount.entrySet()) {
            int teams = me.getValue();
            maxTeams = Math.max(maxTeams, teams);
        }
        return maxTeams;
    }
}
