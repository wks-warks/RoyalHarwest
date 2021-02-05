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
      long count = FR.nextLong();
      String cfStr = getCFStr(count);
      solution.append(cfStr + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static String getCFStr(long count) {
    long ways = 1;
    char[] characters = "codeforces".toCharArray();
    int[] idxFrequency = new int[characters.length];
    for (int i = 0; i < idxFrequency.length; i += 1) {
      idxFrequency[i] = 1;
    }
    int i = 0;
    while (ways < count) {
      int newFreq = idxFrequency[i] + 1;
      ways *= newFreq;
      ways /= idxFrequency[i];
      idxFrequency[i] = newFreq;
      i = (i + 1) % idxFrequency.length;
    }

    StringBuilder sb = new StringBuilder();
    for (i = 0; i < idxFrequency.length; i += 1) {
      for (int j = 0; j < idxFrequency[i]; j += 1) {
        sb.append(characters[i]);
      }
    }
    return sb.toString();
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