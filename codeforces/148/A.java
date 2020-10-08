// Codeforces 148A
import java.util.Scanner;
import java.util.Vector;

public class CF148A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int[] traumaIntervals = new int[4];
        for (int i = 0; i < 4; ++i)
            traumaIntervals[i] = SC.nextInt();
        int dragons = SC.nextInt(); // Number of dragons
        int traumatized = computeTraumatizedDragons(traumaIntervals, dragons);
        System.out.println(traumatized);
    }

    static int addedToGroups;
    // Computes and returns number of dragons traumatized
    static int computeTraumatizedDragons(int[] traumaIntervals, int dragons) {
        int traumatized = 0;
        for (int combine = 1; combine <= 4; ++combine) {
            int numGroups = nPr(4, combine);
            int[][] groups = new int[numGroups][combine];
            addedToGroups = 0;
            addGroups(groups, combine);
            // Created groups

            if (combine % 2 == 1) // Include
                traumatized += traumaImpact(dragons, traumaIntervals, groups);
            else
                traumatized -= traumaImpact(dragons, traumaIntervals, groups);
        }
        return traumatized;
    }

    // Returns impact from given groups
    static int traumaImpact(int dragons, int[] traumaIntervals, int[][] groups) {
        // Compute interval
        int impact = 0;
        for (int g = 0; g < groups.length; ++g) {
            // Compute interval
            int interval = 1;
            for (int j = 0; j < groups[g].length; ++j) {
                int traumaticInterval = traumaIntervals[groups[g][j]];
                long computedInterval = LCM(interval, traumaticInterval);
                interval = (int)Math.min(computedInterval, Integer.MAX_VALUE); // Equivalent for our purposes
            }
            impact += dragons/interval;
        }
        return (int)impact;
    }

    // Fills array of groups
    static void addGroups(int[][] groups, int combine) {
        Vector<Integer> groupVec = new Vector<Integer>();
        groupAddition(groups, combine, groupVec);
    }

    // Fills array of groups
    static void groupAddition(int[][] groups, int combine, Vector<Integer> groupVec) {
        if (addedToGroups == groups.length)
            return;
        if (groupVec.size() == combine) {
            for (int j = 0; j < combine; ++j) {
                groups[addedToGroups][j] = groupVec.get(j);
            }
            addedToGroups += 1;
            return;
        }
        int considerFrom = 0;
        if (groupVec.size() > 0)
            considerFrom = groupVec.get(groupVec.size()-1) + 1;
        for (int i = considerFrom; i < 4; ++i) {
            groupVec.add(i);
            groupAddition(groups, combine, groupVec);
            groupVec.remove(groupVec.size() - 1);
        }
    }

    // Computes nPr (works for small n)
    static int nPr(int n, int r) {
        int ans = 1;
        for (int mul = r+1; mul <= n; ++mul)
            ans *= mul;
        for (int div = 1; div <= n-r; ++div)
            ans /= div;
        return ans;
    }

    // Computes LCM of two numbers
    static long LCM(int num1, int num2) {
        return num1 * num2 / GCD(num1, num2);
    }

    // Computes GCD of two numbers
    static int GCD(int num1, int num2) {
        if (num1 == 0)
            return num2;
        else
            return GCD(num2%num1, num1);
    }
}