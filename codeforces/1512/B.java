import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    for (int t = 0; t < tests; t++) {
      int side = scanner.nextInt();
      String[] grid = new String[side];
      for (int s = 0; s < side; s++) {
        grid[s] = scanner.next();
      }

      String[] result = getResult(grid);
      for (var row : result) {
        System.out.println(row);
      }
    }
  }

  static String[] getResult(String[] grid) {
    int firstRow, firstCol, secondRow, secondCol;
    firstRow = firstCol = secondRow = secondCol = -1;

    outer:
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length(); c++) {
        if (grid[r].charAt(c) == '*') {
          if (firstRow == -1) {
            firstRow = r;
            firstCol = c;
          } else {
            secondRow = r;
            secondCol = c;
            break outer;
          }
        }
      }
    }

    int thirdRow, thirdCol, fourthRow, fourthCol;
    if (firstCol != secondCol && firstRow != secondRow) {
      thirdRow = firstRow;
      thirdCol = secondCol;
      fourthRow = secondRow;
      fourthCol = firstCol;
    } else if (firstCol == secondCol) {
      thirdRow = firstRow;
      fourthRow = secondRow;

      if (firstCol > 0) {
        thirdCol = firstCol - 1;
        fourthCol = firstCol - 1;
      } else {
        thirdCol = firstCol + 1;
        fourthCol = firstCol + 1;
      }
    } else {
      thirdCol = firstCol;
      fourthCol = secondCol;

      if (firstRow > 0) {
        thirdRow = firstRow - 1;
        fourthRow = firstRow - 1;
      } else {
        thirdRow = firstRow + 1;
        fourthRow = firstRow + 1;
      }
    }

    String[] result = new String[grid.length];
    for (int r = 0; r < result.length; r++) {
      StringBuilder row = new StringBuilder(grid[r]);
      if (r == thirdRow) {
        row.setCharAt(thirdCol, '*');
      }
      if (r == fourthRow) {
        row.setCharAt(fourthCol, '*');
      }
      result[r] = row.toString();
    }

    return result;
  }
}