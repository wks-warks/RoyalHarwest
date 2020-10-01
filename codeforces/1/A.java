// Codeforces 1A
import java.util.Scanner;

public class CF1A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int length = SC.nextInt();
        int width = SC.nextInt();
        int tileSide = SC.nextInt();

        long tilesRequired = computeTilesRequired(length, width, tileSide);
        System.out.println(tilesRequired);
    }

    // Computes minimum number of tiles required to pave the theatre
    static long computeTilesRequired(int length, int width, int tileSide) {
        long rows = tilesAcrossSide(length, tileSide);
        long columns = tilesAcrossSide(width, tileSide);
        return rows * columns;
    }

    // Computes Least Integer Greater than sideLength / tileSide
    static int tilesAcrossSide(int sideLength, int tileSide) {
        return (sideLength + tileSide - 1) / tileSide;
    }
}