// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    long n = FR.nextLong();
    long count = getCount(n);
    solution.append(count + "\n");
		PW.print(solution.toString());
    PW.close();
  }

  static long getCount(long n) {
    int sieveSize = (int) Math.sqrt(n);
    List<Boolean> visited = new ArrayList<Boolean>(Collections.nCopies(sieveSize+1, false));
    visited.set(0, true);
    visited.set(1, true);
    long count = 0;
    for (int i = 0; i <= sieveSize; i += 1) {
      if (visited.get(i)) {
        continue;
      } else {
        int increment = getIncrement(i, n);
        count += increment;
        long pow = i;
        while (pow <= sieveSize) {
          visited.set((int) pow, true);
          pow *= i;
        }
        // for (int j = i * i; j <= sieveSize; j += 1) {
          // visited.set(j, true);
        // }
      }
    }
    return n - count;
  }

  static int getIncrement(int i, long n) {
    int maxPow = 0;
    long pow = 1;
    while (pow <= n) {
      maxPow += 1;
      pow *= i;
    }
    return Math.max(0, maxPow-2);
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