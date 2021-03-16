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
      String str = FR.next();
      
      boolean isKPalindrome = checkKPalindrome(str, k);
      solution.append(isKPalindrome ? "YES\n" : "NO\n");
    }

		PW.print(solution.toString());
    PW.close();
  }

  static boolean checkKPalindrome(String str, int k) {
    int kSegmentSize = (str.length() - 1) >> 1;
    if (k > kSegmentSize) {
      return false;
    } else {
      kSegmentSize = k;
    }
    int end = str.length() - 1;
    int maxK = 0;
    
    for (int i = 0; i < kSegmentSize; i += 1) {
      if (str.charAt(i) != str.charAt(end-i)) {
        return k == 0;
      }

      maxK += 1;
    }

    return maxK >= k;
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