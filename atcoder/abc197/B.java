// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    // tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int rows = FR.nextInt();
      int cols = FR.nextInt();
      List<String> grid = new ArrayList<String>(rows);

      int yCoordinate = FR.nextInt() - 1;
      int xCoordinate = FR.nextInt() - 1;
      
      for (int r = 0; r < rows; r += 1) {
        grid.add(FR.next());
      }

      int visibleSquares = getVisibleCount(rows, cols, grid, yCoordinate, xCoordinate);
      solution.append(visibleSquares + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getVisibleCount(int rows, int cols, List<String> grid, int yCoordinate, int xCoordinate) {
    if (grid.get(yCoordinate).charAt(xCoordinate) == '#') {
      return 0;
    }
    int visibleInRow = 1;
    for (int c = xCoordinate - 1; c >= 0; c--) {
      if (grid.get(yCoordinate).charAt(c) == '#') {
        break;
      } else {
        visibleInRow++;
      }
    }

    for (int c = xCoordinate + 1; c < cols; c++) {
      if (grid.get(yCoordinate).charAt(c) == '#') {
        break;
      } else {
        visibleInRow++;
      }
    }

    int visibleInCol = 1;
    for (int r = yCoordinate - 1; r >= 0; r--) {
      if (grid.get(r).charAt(xCoordinate) == '#') {
        break;
      } else {
        visibleInCol++;
      }
    }

    for (int r = yCoordinate + 1; r < rows; r++) {
      if (grid.get(r).charAt(xCoordinate) == '#') {
        break;
      } else {
        visibleInCol++;
      }
    }

    return visibleInRow + visibleInCol - 1;
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException  e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }

    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e)  {
        e.printStackTrace();
      }
      return str;
    }
  }
}