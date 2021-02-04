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
      int desiredPoint = FR.nextInt();
      int timeNeeded = computeTimeNeeded(desiredPoint);
      solution.append(timeNeeded + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }
  
  static int computeTimeNeeded(int desiredPoint) {
    int timeNeeded = 0;
    int left = 1;
    int right = 45_000;
    while (left <= right) {
      int mid = (left + right) >> 1;
      int diff = sumUpto(mid) - desiredPoint;
      if (diff >= 0) {
        timeNeeded = mid;
        right = mid-1;
      } else {
        left = mid + 1;
      }
    }
    return timeNeeded;
  }

  static int sumUpto(int n) {
    return (n * (n+1)) >> 1;
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