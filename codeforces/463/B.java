//Codeforces 463B 
import java.util.Scanner;

public class CF463B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int pylons = SC.nextInt();
        int[] heights = new int[pylons+1];
        for (int i = 1; i <= pylons; i += 1) {
            heights[i] = SC.nextInt();
        }
        long totalExpenditure = getExpenditure(heights);
        System.out.println(totalExpenditure);
    }

    // Computes and returns incurred expenditure
    static long getExpenditure(int[] heights) {
        long energy = 0;
        long cost = 0;
        for (int i = 1; i < heights.length; i += 1) {
            energy += heights[i-1] - heights[i];
            if (energy < 0) {
                cost -= energy;
                energy = 0;
            }
        }
        return cost;
    }
}
