// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int rows = FR.nextInt();
      int verticalCost = FR.nextInt();
      int horizontalCost = FR.nextInt();
      List<Integer> positions = new ArrayList<Integer>(rows);
      for (int r = 0; r < rows; r += 1) {
        positions.add(FR.nextInt());
      }
      int minCost = getMinCost(positions, verticalCost, horizontalCost);
      solution.append(minCost + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getMinCost(List<Integer> positions, int verticalCost, int horizontalCost) {
    int minCost = horizontalCost + Math.min(horizontalCost, verticalCost);
    for (int i = 0; i < positions.size(); i += 1) {
      if (verticalShiftAndMove(positions, i)) {
        minCost = Math.min(minCost, verticalCost);
      }
      int horizontalShifts = horizontalShiftAndMove(positions, i);
      if (horizontalShifts == 1) {
        minCost = Math.min(minCost, horizontalCost);
      } else if (horizontalShifts == 0) {
        return 0;
      }
    }
    return minCost;
  }

  static boolean verticalShiftAndMove(List<Integer> positions, int row) {
    if (row > 0 && !positions.get(row).equals(positions.get(row-1))) {
      return true;
    } else {
      return row+1 < positions.size() && !positions.get(row).equals(positions.get(row+1));
    }
  }

  static int horizontalShiftAndMove(List<Integer> positions, int row) {
    int upperDiff = Integer.MIN_VALUE;
    if (row > 0) {
      upperDiff = Math.abs(positions.get(row) - positions.get(row-1));
    }
    int lowerDiff = Integer.MIN_VALUE;
    if (row+1 < positions.size()) {
      lowerDiff = Math.abs(positions.get(row) - positions.get(row+1));
    }
    if (Math.max(lowerDiff, upperDiff) >= 2) {
      return 0;
    } else if (Math.max(lowerDiff, upperDiff) == 0) {
      return -1;
    } else {
      return 1;
    }
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