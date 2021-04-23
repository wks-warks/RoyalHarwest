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
      long num = FR.nextLong();
      int divisorCount = getDivisorCount(num);
      solution.append(divisorCount + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getDivisorCount(long num) {
    if (num % 2050 > 0) {
      return -1;
    }

    long div = 2050;
    while (div <= 100_000_000_000_000_000L) {
      div *= 10;
    }

    int divisorCount = 0;
    while (num > 0) {
      divisorCount += num / div;
      num %= div;
      div /= 10;
    }

    return divisorCount;
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