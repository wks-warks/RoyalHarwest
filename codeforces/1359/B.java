//Codeforces 1359B 
import java.util.Scanner;

public class CF1359B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int rows = SC.nextInt();
            int cols = SC.nextInt();
            int singleTilePrice = SC.nextInt();
            int doubleTilePrice = SC.nextInt();
            String[] theatre = new String[rows];
            for (int r = 0; r < rows; ++r)
                theatre[r] = SC.next();
            int minCost = computeMinCost(theatre, singleTilePrice, doubleTilePrice);
            System.out.println(minCost);
        }
    }

    // Computes and returns minimum cost of paving the theatre
    static int computeMinCost(String[] theatre, int singleTilePrice, int doubleTilePrice) {
        if (doubleTilePrice < 2 * singleTilePrice) {
            int cost = 0;
            for (int r = 0; r < theatre.length; ++r)
                for (int c = 0; c < theatre[r].length(); ++c)
                    if (theatre[r].charAt(c) == '.') {
                        if (c + 1 < theatre[r].length() && theatre[r].charAt(c+1) == '.') {
                            c += 1;
                            cost += doubleTilePrice;
                        }
                        else
                            cost += singleTilePrice;
                    }
            return cost;
        }
        else {
            int cost = 0;
            for (int r = 0; r < theatre.length; ++r)
                for (int c = 0; c < theatre[r].length(); ++c)
                    if (theatre[r].charAt(c) == '.')
                        cost += singleTilePrice;
            return cost;
        }
    }
}
