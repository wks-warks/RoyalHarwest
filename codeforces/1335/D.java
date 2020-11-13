//Codeforces 1335D 
import java.util.Scanner;

public class CF1335D {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            String[] sudoku = new String[9];
            for (int r = 0; r < sudoku.length; ++r)
                sudoku[r] = SC.next();
            String[] antiSudoku = getAntiSudoku(sudoku);
            out(antiSudoku);
        }
    }

    // Prints out the strings passed
    static void out(String[] antiSudoku) {
        for (int r = 0; r < antiSudoku.length; ++r)
            System.out.println(antiSudoku[r]);
    }

    // Computes and returns anti-sudoku
    static String[] getAntiSudoku(String[] sudoku) {
        String[] antiSudoku = new String[sudoku.length];
        for (int r = 0; r < sudoku.length; ++r) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < sudoku[r].length(); ++c)
                if (sudoku[r].charAt(c) == '1')
                    sb.append('2');
                else
                    sb.append(sudoku[r].charAt(c));
            antiSudoku[r] = sb.toString();
        }
        return antiSudoku;
    }
}
