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
      String s = FR.next();
      long result = getResult(s);
      solution.append(result +"\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static long getResult(String s) {
    int[] prefixSum = new int[s.length()+1];
    int i = 0;
    while (i < s.length()) {
      if (s.charAt(i) == '-') {
        prefixSum[++i] = prefixSum[i-1] - 1;
      } else {
        prefixSum[++i] = prefixSum[i-1] + 1;
      }
    }

    long result = 0;
    // int rounds = Math.max(1 - prefixSum[s.length()], 1);
    int minPFSum = Integer.MAX_VALUE;
    for (i = 1; i < prefixSum.length; i++) {
      minPFSum = Math.min(minPFSum, prefixSum[i]);
    }
    int rounds = 1 + Math.max(0, -minPFSum);
    int roundsSettled = 0;
    for (i = 1; i < prefixSum.length; i++) {
      if (prefixSum[i] + roundsSettled < 0 && roundsSettled < rounds) {
        roundsSettled+= 1;
        // System.out.println("ROUNDS SETTLED : " + roundsSettled);
        result += i;
      }
    }

    if (roundsSettled < rounds) {
      result += (long) (rounds - roundsSettled) * s.length();
    }

    // for (i = 0; i < prefixSum.length; i++) {
      // System.out.printf("i: %d, pf[i]: %d\n", i, prefixSum[i]);
    // }

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