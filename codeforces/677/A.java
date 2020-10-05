// Codeforces 677A
import java.util.Scanner;

public class CF677A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numFriends = SC.nextInt(); // Number of friends
        int heightLimit = SC.nextInt();
        int[] heights = new int[numFriends];
        for (int f = 0; f < numFriends; ++f)
            heights[f] = SC.nextInt();
        int roadWidth = computeRoadWidth(heights, heightLimit); // Minimum road width required
        System.out.println(roadWidth);
    }

    // Computes and returns minimum required width of the road
    static int computeRoadWidth(int[] heights, int heightLimit) {
        int requiredWidth = 0;
        for (int f = 0; f < heights.length; ++f)
            if (heights[f] > heightLimit) // If true - needs to bend
                requiredWidth += 2;
            else
                requiredWidth += 1;
        return requiredWidth;
    }
}