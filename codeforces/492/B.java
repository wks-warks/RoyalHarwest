// Codeforces 492B
import java.util.Scanner;
import java.util.Arrays;

public class CF492B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int lanterns = SC.nextInt();
        int streetLen = SC.nextInt();
        int[] lanternPos = new int[lanterns];
        for (int i = 0; i < lanterns; ++i)
            lanternPos[i] = SC.nextInt();
        double lightRadius = findRadius(lanternPos, streetLen);
        System.out.println(lightRadius);
    }

    // Computes and returns minimum required radius of light
    static double findRadius(int[] lanternPos, int streetLen) {
        Arrays.sort(lanternPos);
        double radius = lanternPos[0];
        for (int i = 1; i < lanternPos.length; ++i)
            radius = Math.max(radius, (double)(lanternPos[i]-lanternPos[i-1])/2);
        radius = Math.max(radius, streetLen - lanternPos[lanternPos.length-1]);
        return radius;
    }
}