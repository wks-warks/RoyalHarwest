// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  static int mountainCount, boulderCount;
  static int[] mountainHeights;
  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      mountainCount = FR.nextInt();
      boulderCount = FR.nextInt();
      mountainHeights = new int[mountainCount];
      for (int m = 0; m < mountainCount; m += 1) {
        mountainHeights[m] = FR.nextInt();
      }
      int lastBoulderIdx = getLastBoulderIdx();
      solution.append(lastBoulderIdx + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getLastBoulderIdx() {
    for (int i = 0; i < mountainCount; i += 1) {
      if (i == mountainCount-1) {
        return -1;
      } else {
        int diff = mountainHeights[i+1] - mountainHeights[i];
        if (diff > 0) {
          mountainHeights[i] += 1;
          boulderCount -= 1;
          if (boulderCount == 0) {
            return i+1;
          } else {
            return getLastBoulderIdx();
          }
          // if (diff >= boulderCount) {
            // return i+1;
          // } else {
            // boulderCount -= diff;
            // mountainHeights[i] = mountainHeights[i+1];
            // return getLastBoulderIdx();
          // }
        }
      }
    }
    return -1;
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