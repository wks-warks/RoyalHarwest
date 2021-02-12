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
      int x = FR.nextInt();
      int y = FR.nextInt();
      long specialPairCount = getSpecialPairCount(x, y);
      solution.append(specialPairCount + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static long getSpecialPairCount(int x, int y) {
    int maxTerms = Math.min(sqrt(x+1), y)-1;
    long specialPairCount = 0;
    for (int i = 1; i <= maxTerms; i += 1) {
      int add = getAddition(i, y, x);
      specialPairCount += add;
    }
    return specialPairCount;
  }

  static int getAddition(int termCount, int maxB, int maxA) {
    int add = 0;
    int maxN = 1;
    int left = 1;
    int right = Math.min(maxA-1, maxB);
    while (left <= right) {
      int mid = (left + right) >> 1;
      if ((long) termCount * mid + termCount <= maxA) {
        maxN = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    int termsWithTermCount = Math.max(maxN - termCount, 0);
    return termsWithTermCount;
  }

  static int sqrt(int num) {
    int root = 1;
    long left = 1;
    long right = num;
    while (left <= right) {
      long mid = (left + right) >> 1;
      if (mid * mid <= num) {
        root = (int) mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return root;
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