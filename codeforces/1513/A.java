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
      int k = FR.nextInt();
      int[] result = getResult(n, k);
      for (var num : result) {
        solution.append(num + " ");
      }
      solution.append("\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int[] getResult(int n, int k) {
    if (k > (n - 1) >> 1) {
      return new int[] {-1};
    }

    int[] result = new int[n];
    result[0] = 1;
    for (int p = 0; p < k; p++) {
      int i = 1 + (p<<1);
      result[i] = 3 + (p<<1);
      result[i+1] = result[i]-1;
    }

    for (int i = 1 + (k<<1); i < n; i++) {
      result[i] = i+1;
    }
    return result;
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

/*
  1 3 2 5 4 7 6
  - P - P - P -
  1 3 2 5 4 6
  - P - P - -
  1 3 2 5 4
  - P - P -
*/