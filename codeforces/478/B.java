//Codeforces 478B 
import java.util.Scanner;

public class CF478B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int participants = SC.nextInt();
        int teams = SC.nextInt();
        NumberOfPairs fp = getFrienshipPairRange(participants, teams);
        long minPairs = fp.getMinPairs();
        long maxPairs = fp.getMaxPairs();
        System.out.println(minPairs + " " + maxPairs);
    }

    // Computes and returns number of pairs class - maximum and minimum pairs formed
    static NumberOfPairs getFrienshipPairRange(int participants, int teams) {
        // Going with symmetry - closest to evenness and farthest from evenness in distribution
        // Option 1 - larger case
        long option1 = nC2(participants-teams+1); // Allocating all but 1 team with 1 member each
        
        // Option 2 - smaller case
        int teamSize = participants/teams; // All teams have at least this many members
        int teamsWithMore = participants % teams; // These many teams have 1 more member than teamsize
        long option2 = (teams-teamsWithMore) * nC2(teamSize) + teamsWithMore*nC2(teamSize+1);
        return new NumberOfPairs(option2, option1);
    }

    // Computes and returns nC2 - combinations
    static long nC2(int n) {
        if (n == 1)
            return 0;
        else
            return (long) n*(n-1)/2;
    }
}

// Stores minimum and maximum number of pairs formed
class NumberOfPairs {
    private long minPairs;
    private long maxPairs;
    public NumberOfPairs(long min, long max) {
        minPairs = min;
        maxPairs = max;
    }
    // Getter methods
    public long getMinPairs() {
        return minPairs;
    }
    public long getMaxPairs() {
        return maxPairs;
    }
}
