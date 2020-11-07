//Codeforces 1391B 
import java.util.Scanner;

/*
    Logic:
    Note that since we must find a solution that ensures safe retreival of luggage from ANY cell,
    therefore no cell should lead to luggage moving out of the belt,
    consequently no cell should push luggage out of the belt and if this holds then the previous will too.
    Thus, cells in the last column must all send luggage Downwards and cells at the bottom must all send luggage Rightwards.
*/


public class CF1391B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int rows = SC.nextInt();
            int cols = SC.nextInt();
            String[] belt = new String[rows];
            for (int r = 0; r < rows; ++r)
                belt[r] = SC.next();
            int changesRequired = countChangesRequired(rows, cols, belt);
            System.out.println(changesRequired);
        }        
    }

    // Counts and returns number of cells that must be changes
    static int countChangesRequired(int rows, int cols, String[] belt) {
        int changes = 0;
        int r, c;
        for (r = 0; r < rows-1; ++r)
            if (belt[r].charAt(cols-1) == 'R')
                changes += 1;
        for (c = 0; c < cols-1; ++c)
            if (belt[r].charAt(c) == 'D')
                changes += 1;
        return changes;
    }
}
