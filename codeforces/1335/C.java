// Codeforces 1335C
import java.util.Scanner;
import java.util.TreeMap;

public class CF1335C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int students = SC.nextInt();
            int maxTeamSize = computeMaxTeamSize(students);
            System.out.println(maxTeamSize);
        }
    }

    // Computes and returns maximum possible size of teams
    static int computeMaxTeamSize(int students) {
        IntPair skillDistribution = getDistributionPair(students);
        int distinctSkilled = skillDistribution.getV1();
        int maxSameSkilled = skillDistribution.getV2();
        return Math.min(Math.max(distinctSkilled, maxSameSkilled)-1,
                        Math.min(distinctSkilled, maxSameSkilled)); // If both are equal, it returns value-1(as 1 player can't belong to both teams), else it returns minimum of the two
    }

    static IntPair getDistributionPair(int students) {
        TreeMap<Integer, Integer> skillMap = new TreeMap<>();
        int maxSame = 1;
        for (int s = 0; s < students; ++s) {
            int skill = SC.nextInt();
            if (skillMap.containsKey(skill)) {
                int presentCount = skillMap.get(skill);
                maxSame = Math.max(maxSame, presentCount+1);
                skillMap.put(skill, presentCount+1);
            }
            else
                skillMap.put(skill, 1);
        }
        int distinct = skillMap.size();
        return new IntPair(distinct, maxSame);
    }
}

class IntPair {
    private int var1;
    private int var2;
    public IntPair(int val1, int val2) {
        this.var1 = val1;
        this.var2 = val2;
    }
    public int getV1() {
        return var1;
    }
    public int getV2() {
        return var2;
    }
}