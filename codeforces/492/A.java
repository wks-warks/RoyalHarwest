// Codeforces 492A
import java.util.Scanner;

public class CF492A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int cubes = SC.nextInt();
        int pyramidHeight = computeHeight(cubes);
        System.out.println(pyramidHeight);
    }

    // Computes and returns height of pyramid
    static int computeHeight(int cubes) {
        int remaining = cubes;
        int height = 0;
        while (remaining >= 0) {
            remaining -= cubesForLevel(height+1);
            height += 1;
        }
        return height - 1;
    }

    // Computes and returns number of cubes required at given level
    static int cubesForLevel(int level) {
        return level * (level+1) / 2;
    }
}