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
      int px = FR.nextInt();
      int py = FR.nextInt();
      String moves = FR.next();
      boolean reachable = checkReachable(px, py, moves);  
      solution.append(reachable ? "YES\n" : "NO\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static boolean checkReachable(int px, int py, String moves) {
    int up, down, left, right;
    up = down = left = right = 0;
    for (var element : moves.toCharArray()) {
      switch(element) {
        case 'U' : up += 1;
        break;
        case 'D' : down += 1;
        break;
        case 'L' : left += 1;
        break;
        case 'R' : right += 1;
      }
    }
    boolean horizontallyPossible = checkReach(px, right, left);
    boolean verticallyPossible = checkReach(py, up, down);
    return horizontallyPossible && verticallyPossible;
  }

  static boolean checkReach(int target, int positives, int negatives) {
    if (target >= 0) {
      return positives >= target;
    } else {
      return negatives >= -target;
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