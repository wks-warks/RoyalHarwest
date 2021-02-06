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
      int velocity = FR.nextInt();
      int vanishStart = FR.nextInt();
      int vanishEnd = FR.nextInt();
      int consideredDistance = FR.nextInt();
      boolean canHit = !isInvisible(velocity, vanishStart, vanishEnd, consideredDistance);
      solution.append(canHit ? "Yes\n" : "No\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static boolean isInvisible(int velocity, int vanishStart, int vanishEnd, int consideredDistance) {
    return ((velocity * vanishStart) <= consideredDistance && consideredDistance <= (velocity * vanishEnd));
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