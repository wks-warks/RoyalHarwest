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
      long num = FR.nextLong();
      int minF = getMinF(num);
      solution.append(minF + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getMinF(long num) {
    int root = (int) Math.sqrt(num);
    
    int minF = Integer.MAX_VALUE;
    for (int i = 1; i <= root; i += 1) {
      if (num % i == 0) {
        int firstDigits = countDigits(i);
        int secondDigits = countDigits(num / i);
        minF = Math.min(minF, Math.max(firstDigits, secondDigits));
      }
    }

    return minF;
  }

  static int countDigits(long num) {
    return Long.toString(num).length();
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