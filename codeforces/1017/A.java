// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    for (int t = 0; t < tests; ++t) {
      int students = FR.nextInt();
      int thomasScore = getScore();
      int rank = 1;
      for (int s = 1; s < students; s++) {
        int score = getScore();
        if (score > thomasScore) {
          rank += 1;
        }
      }
      solution.append(rank +"\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getScore() {
    int a = FR.nextInt();
    int b = FR.nextInt();
    int c = FR.nextInt();
    int d = FR.nextInt();
    return a + b + c + d;
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