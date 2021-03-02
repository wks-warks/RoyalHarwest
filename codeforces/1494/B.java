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
      int gridSize = FR.nextInt();
      int topNeeded = FR.nextInt();
      int rightNeeded = FR.nextInt();
      int bottomNeeded = FR.nextInt();
      int leftNeeded = FR.nextInt();
      List<Boolean> cornersColored = new ArrayList<Boolean>(Collections.nCopies(4, false));
      boolean colorable = isColorable(topNeeded, rightNeeded, bottomNeeded, leftNeeded, gridSize, cornersColored);
      solution.append(colorable ? "Yes\n" : "No\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static boolean isColorable(int topNeeded, int rightNeeded, int bottomNeeded, int leftNeeded, int gridSize, List<Boolean> cornersColored) {
    if (topNeeded == gridSize) {
      topNeeded -= 2;
      leftNeeded -= 1;
      cornersColored.set(0, true);
      rightNeeded -= 1;
      cornersColored.set(1, true);
      return isColorable(topNeeded, rightNeeded, bottomNeeded, leftNeeded, gridSize, new ArrayList<Boolean>(cornersColored));
    }
    if (topNeeded == gridSize - 1) {
      if (cornersColored.get(0)) {
        if (!cornersColored.get(1)) {
          cornersColored.set(1, true);
          topNeeded -= 1;
          rightNeeded -= 1;
        } else {
          return false;
        }
      } else if (cornersColored.get(1)) {
        cornersColored.set(0, true);
        topNeeded -= 1;
        leftNeeded -= 1;
      } else {
        topNeeded -= 1;
        leftNeeded -= 1;
        cornersColored.set(0, true);
        boolean answer = isColorable(topNeeded, rightNeeded, bottomNeeded, leftNeeded, gridSize, new ArrayList<Boolean>(cornersColored));
        if (answer) {
          return true;
        }
        leftNeeded += 1;
        cornersColored.set(0, false);
        rightNeeded -= 1;
        cornersColored.set(1, true);
        return isColorable(topNeeded, rightNeeded, bottomNeeded, leftNeeded, gridSize, new ArrayList<Boolean>(cornersColored));
      }
    }
    if (bottomNeeded == gridSize) {
      bottomNeeded -= 2;
      leftNeeded -= 1;
      cornersColored.set(2, true);
      rightNeeded -= 1;
      cornersColored.set(3, true);
      return isColorable(topNeeded, rightNeeded, bottomNeeded, leftNeeded, gridSize, new ArrayList<Boolean>(cornersColored));
    }
    if (bottomNeeded == gridSize - 1) {
      if (cornersColored.get(2)) {
        if (!cornersColored.get(3)) {
          cornersColored.set(3, true);
          bottomNeeded -= 1;
          rightNeeded -= 1;
        } else {
          return false;
        }
      } else if (cornersColored.get(3)) {
        cornersColored.set(2, true);
        bottomNeeded -= 1;
        leftNeeded -= 1;
      } else {
        bottomNeeded -= 1;
        leftNeeded -= 1;
        cornersColored.set(2, true);
        boolean answer = isColorable(topNeeded, rightNeeded, bottomNeeded, leftNeeded, gridSize, new ArrayList<Boolean>(cornersColored));
        if (answer) {
          return true;
        }
        leftNeeded += 1;
        cornersColored.set(2, false);
        rightNeeded -= 1;
        cornersColored.set(3, true);
        return isColorable(topNeeded, rightNeeded, bottomNeeded, leftNeeded, gridSize, new ArrayList<Boolean>(cornersColored));
      }
    }
    if (rightNeeded == gridSize) {
      rightNeeded -= 2;
      topNeeded -= 1;
      cornersColored.set(1, true);
      bottomNeeded -= 1;
      cornersColored.set(3, true);
    }
    if (rightNeeded == gridSize - 1) {
      if (cornersColored.get(1)) {
        if (!cornersColored.get(3)) {
          cornersColored.set(3, true);
          rightNeeded -= 1;
          bottomNeeded -= 1;
        } else {
          return false;
        }
      } else if (cornersColored.get(3)) {
        cornersColored.set(1, true);
        rightNeeded -= 1;
        topNeeded -= 1;
      } else {
        rightNeeded -= 1;
        topNeeded -= 1;
        cornersColored.set(1, true);
        boolean answer = isColorable(topNeeded, rightNeeded, bottomNeeded, leftNeeded, gridSize, new ArrayList<Boolean>(cornersColored));
        if (answer) {
          return true;
        }
        topNeeded += 1;
        cornersColored.set(1, false);
        bottomNeeded -= 1;
        cornersColored.set(3, true);
        return isColorable(topNeeded, rightNeeded, bottomNeeded, leftNeeded, gridSize, new ArrayList<Boolean>(cornersColored));
      }
    }
    if (leftNeeded == gridSize) {
      leftNeeded -= 2;
      topNeeded -= 1;
      cornersColored.set(0, true);
      bottomNeeded -= 1;
      cornersColored.set(2, true);
    }
    if (leftNeeded == gridSize - 1) {
      if (cornersColored.get(0)) {
        if (!cornersColored.get(2)) {
          cornersColored.set(2, true);
          leftNeeded -= 1;
          bottomNeeded -= 1;
        } else {
          return false;
        }
      } else if (cornersColored.get(2)) {
        cornersColored.set(0, true);
        leftNeeded -= 1;
        topNeeded -= 1;
      } else {
        leftNeeded -= 1;
        topNeeded -= 1;
        cornersColored.set(0, true);
        boolean answer = isColorable(topNeeded, rightNeeded, bottomNeeded, leftNeeded, gridSize, new ArrayList<Boolean>(cornersColored));
        if (answer) {
          return true;
        }
        topNeeded += 1;
        cornersColored.set(0, false);
        bottomNeeded -= 1;
        cornersColored.set(3, true);
        return isColorable(topNeeded, rightNeeded, bottomNeeded, leftNeeded, gridSize, new ArrayList<Boolean>(cornersColored));
      }
    }

    return topNeeded >= 0 && rightNeeded >= 0 && bottomNeeded >= 0 && leftNeeded >= 0;
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