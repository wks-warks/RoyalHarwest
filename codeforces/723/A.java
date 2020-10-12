// Codeforces 723A
import java.util.Scanner;
import java.util.Arrays;

public class CF723A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int[] friendsPos = new int[3];
        for (int i = 0; i < friendsPos.length; ++i)
            friendsPos[i] = SC.nextInt();
        int travelDistance = computeTravelDistance(friendsPos);
        System.out.println(travelDistance);
    }

    // Computes and returns distance to be traveled
    static int computeTravelDistance(int[] positions) {
        Arrays.sort(positions);
        return positions[2] - positions[0];
        // For any point chosen, the sum of distances traveled by the leftmost and rightmost in total = right-left = constant
        // Therefore it depends on distance from the 3rd one. We minimize that by keeping the meeting point where s/he is
    }
}