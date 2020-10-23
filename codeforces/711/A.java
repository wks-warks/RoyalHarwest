// Codeforces 711A
import java.util.Scanner;

public class CF711A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int rows = SC.nextInt(); // Number of rows in the bus
        String[] seating = new String[rows];
        for (int r = 0; r < rows; ++r)
            seating[r] = SC.next();
        solutionArrangement(seating);
    }

    // Checks whether a solution is possible and prints the arrangement if so.
    static void solutionArrangement(String[] seating) {
        boolean possible = false;
        for (int r = 0; r < seating.length; ++r) {
            if (seating[r].substring(0, 2).equals("OO")) {
                seating[r] = "++" + seating[r].substring(2); // Changing the row for the solution
                possible = true;
                break;
            }
            else if (seating[r].substring(3, 5).equals("OO")) {
                seating[r] = seating[r].substring(0, 3) + "++"; // Changing the row for the solution
                possible = true;
                break;
            }
        }
        if (possible) {
            System.out.println("YES");
            for (int r = 0; r < seating.length; ++r)
                System.out.println(seating[r]);
        }
        else
            System.out.println("NO");
    }
}