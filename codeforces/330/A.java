//Codeforces 330A 
import java.util.Scanner;

public class CF330A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int rows = SC.nextInt();
        int columns = SC.nextInt();
        String[] cake = new String[rows];
        for (int r = 0; r < rows; ++r)
                cake[r] = SC.next();
        int eatableCells = countEatableCells(cake);
        System.out.println(eatableCells);       
    }

    // Counts and returns number of eatable cells
    static int countEatableCells(String[] cake) {
        boolean[] strawberryInRow = new boolean[cake.length];
        boolean[] strawberryInColumn = new boolean[cake[0].length()];
        for (int r = 0; r < cake.length; ++r) {
            for (int c = 0; c < cake[0].length(); ++c) {
                if (cake[r].charAt(c) == 'S') {
                    strawberryInRow[r] = true;
                    strawberryInColumn[c] = true;
                }
            }
        }

        int eatableCells = 0;
        for (int r = 0; r < cake.length; ++r) {
            for (int c = 0; c < cake[0].length(); ++c) {
                if (cake[r].charAt(c) != 'S' && (!strawberryInRow[r] || !strawberryInColumn[c]))
                    eatableCells += 1;
            }
        }
        return eatableCells;
    }
}