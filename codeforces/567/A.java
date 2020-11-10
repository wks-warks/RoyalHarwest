//Codeforces 567A 
import java.util.Scanner;

public class CF567A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int cities = SC.nextInt();
        int[] pos = new int[cities];
        for (int c = 0; c < cities; ++c)
            pos[c] = SC.nextInt();
        Costs costs = getCosts(cities, pos);
        out(costs, cities);
    }

    // Computes and returns costs associated to each city
    static Costs getCosts(int cities, int[] pos) {
        int[] minCosts = new int[cities];
        int[] maxCosts = new int[cities];
        minCosts[0] = pos[1]-pos[0];
        maxCosts[0] = pos[cities-1]-pos[0];
        for (int c = 1; c < cities-1; ++c) {
            minCosts[c] = Math.min(pos[c]-pos[c-1], pos[c+1]-pos[c]);
            maxCosts[c] = Math.max(pos[c]-pos[0], pos[cities-1]-pos[c]);
        }
        minCosts[cities-1] = pos[cities-1]-pos[cities-2];
        maxCosts[cities-1] = maxCosts[0];
        return new Costs(minCosts, maxCosts);
    }

    // Prints output corresponding to the minimum and maximum costs
    static void out(Costs costs, int cities) {
        int[] minCosts = costs.getMinCosts();
        int[] maxCosts = costs.getMaxCosts();
        for (int c = 0; c < cities; ++c)
            System.out.println(minCosts[c] + " " + maxCosts[c]);
    }
}

class Costs {
    private int[] minCosts, maxCosts;
    public Costs(int[] minCosts, int[] maxCosts) {
        this.minCosts = minCosts;
        this.maxCosts = maxCosts;
    }

    // Getter methods
    public int[] getMinCosts() {
        return minCosts;
    }
    public int[] getMaxCosts() {
        return maxCosts;
    }
}