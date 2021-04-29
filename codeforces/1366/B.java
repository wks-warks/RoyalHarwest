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
      int n = FR.nextInt();
      int x = FR.nextInt();
      int m = FR.nextInt();

      int[][] moves = new int[m][2];
      for (int i = 0; i < moves.length; i++) {
        moves[i][0] = FR.nextInt();
        moves[i][1] = FR.nextInt();
      }

      int kCount = getKCount(x, moves);
      solution.append(kCount + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getKCount(int x, int[][] moves) {
    int left = x;
    int right = x;

    for (var move : moves) {
      int lLimit = move[0];
      int rLimit = move[1];

      if (lLimit <= left && rLimit >= left) {
        left = lLimit;
      }
      if (rLimit >= right && lLimit <= right) {
        right = rLimit;
      }
    }

    return right - left + 1;
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