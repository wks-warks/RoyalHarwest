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
      memo = new HashMap<Integer, Integer>();
      int a = FR.nextInt();
      int b = FR.nextInt();
      int minMoves = b > 1 ? 0 : 1;
      b = Math.max(2, b);
      minMoves += getMinMoves(a, b, 0);
      solution.append(minMoves + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static Map<Integer, Integer> memo;
  static int getMinMoves(int a, int b, int deviation) {
    if (deviation > 32) {
      return 100;
    } else {
      if (memo.containsKey(b)) {
        return memo.get(b);
      } else {
        int totalMoves = deviation + movesUntil(a, b);
        int alt = getMinMoves(a, b+1, deviation+1);
        memo.put(b, Math.min(totalMoves, alt));
        return memo.get(b);
      }
    }
  }

  static int movesUntil(long a, long b) {
    if (a == 0) {
      return 0;
    }
    int moves = 1;
    long mul = b;
    while (a >= b) {
      moves += 1;
      b *= mul;
    }
    return moves;
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