import java.util.Scanner;
import java.util.TreeSet;

public class CF631B {
    static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        int rows = SC.nextInt();
        int cols = SC.nextInt();
        int operationCount = SC.nextInt();
        int[][] operations = new int[operationCount][3];
        for (int op = 0; op < operationCount; ++op) {
            operations[op][0] = SC.nextInt();
            operations[op][1] = SC.nextInt();
            operations[op][2] = SC.nextInt();
        }
        int[][] table = getPaintedTable(rows, cols, operations);

        StringBuilder solution = new StringBuilder();
        for (int[] row : table) {
            for (int col : row) {
                solution.append(col);
                solution.append(' ');
            }
            solution.append('\n');
        }
        System.out.print(solution);
    }

    // Paints and returns table after performing all operations
    static int[][] getPaintedTable(int rows, int cols, int[][] operations) {
        int[][] table = new int[rows][cols];
        for (int r = 0; r < rows; ++r)
            for (int c = 0; c < cols; ++c)
                table[r][c] = 0;
            
        TreeSet<Integer> paintedRows = new TreeSet<>();
        TreeSet<Integer> paintedCols = new TreeSet<>();
        for (int i = operations.length - 1; i >= 0; --i) {
            if (operations[i][0] == 1) {
                int row = operations[i][1]-1;
                if (paintedRows.contains(row))
                    continue;
                paintedRows.add(row);
                for (int c = 0; c < cols; ++c) {
                    if (table[row][c] == 0)
                        table[row][c] = operations[i][2];
                }
            }
            else {
                int col = operations[i][1]-1;
                if (paintedCols.contains(col))
                    continue;
                paintedCols.add(col);
                for (int r = 0; r < rows; ++r) {
                    if (table[r][col] == 0)
                        table[r][col] = operations[i][2];
                }
            }
        }
        return table;
    }
}