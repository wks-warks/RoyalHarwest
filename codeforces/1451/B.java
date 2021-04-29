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
      int q = FR.nextInt();
      String s = FR.next();
      Solver solver = new Solver(n, s);

      for (int i = 0; i < q; i++) {
        int left = FR.nextInt();
        int right = FR.nextInt();

        boolean found = solver.find(left, right);
        solution.append(found ? "YES\n" : "NO\n");
      }
    }
		PW.print(solution.toString());
    PW.close();
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

class Solver {
  boolean[] zeroBefore, zeroAfter, oneBefore, oneAfter;
  String s;

  public Solver(int n, String s) {
    zeroBefore = new boolean[n];
    zeroAfter = new boolean[n];
    oneBefore = new boolean[n];
    oneAfter = new boolean[n];

    for (int i = 1; i < n-1; i++) {
      zeroBefore[i] = zeroBefore[i-1] || s.charAt(i-1) == '0';
      oneBefore[i] = oneBefore[i-1] || s.charAt(i-1) == '1';
    }
    for (int i = n-2; i > 0; i--) {
      zeroAfter[i] = zeroAfter[i+1] || s.charAt(i+1) == '0';
      oneAfter[i] = oneAfter[i+1] || s.charAt(i+1) == '1';
    }

    this.s = s;
  }

  public boolean find(int left, int right) {
    boolean found = false;
    if (s.charAt(left-1) == '1') {
      found |= oneBefore[left-1];
    } else {
      found |= zeroBefore[left-1];
    }

    if (s.charAt(right-1) == '1') {
      found |= oneAfter[right-1];
    } else {
      found |= zeroAfter[right-1];
    }

    return found;
  }
}