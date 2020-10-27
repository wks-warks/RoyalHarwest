//Codeforces 459B 
import java.util.Scanner;

public class CF459B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int flowers = SC.nextInt();
        int[] beauty = new int[flowers];
        for (int f = 0; f < flowers; ++f)
            beauty[f] = SC.nextInt();
        AnswerWaysPair solution = getSolution(beauty);
        out(solution);
    }

    // Computes and returns solution pair
    static AnswerWaysPair getSolution(int[] beauty) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < beauty.length; ++i) {
            min = Math.min(min, beauty[i]);
            max = Math.max(max, beauty[i]);
        }
        int minCount = 0;
        int maxCount = 0;
        for (int i = 0; i < beauty.length; ++i) {
            if (beauty[i] == min)
                minCount += 1;
            if (beauty[i] == max)
                maxCount += 1;
        }
        int maxDiff = max-min;
        long ways = (max==min)?nC2(beauty.length) : (long)minCount*maxCount;
        return new AnswerWaysPair(maxDiff, ways);
    }
    
    // Computes and returns nC2 (combinations)
    static long nC2(int n) {
        return (long)n*(n-1)/2;
    }

    // Prints output corresponding to AnswerWaysPair
    static void out(AnswerWaysPair solution) {
        int maxDiff = solution.getMaxDiff();
        long ways = solution.getWays();
        System.out.println(maxDiff + " " + ways);
    }
}

class AnswerWaysPair {
    private int maxDiff;
    private long ways;
    public AnswerWaysPair(int maxDiff, long ways) {
        this.maxDiff = maxDiff;
        this.ways = ways;
    }
    // Getter methods
    public int getMaxDiff() {
        return maxDiff;
    }
    public long getWays() {
        return ways;
    }
}