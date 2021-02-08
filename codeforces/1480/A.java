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
      String input = FR.next();
      String output = getOutput(input);
      solution.append(output + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static String getOutput(String input) {
    StringBuilder output = new StringBuilder();
    boolean aliceMoves = true;
    for (var ch : input.toCharArray()) {
      char outCh;
      if (aliceMoves) {
        outCh = aliceChange(ch);
      } else {
        outCh = bobChange(ch);
      }
      output.append(outCh);
      aliceMoves = !aliceMoves;
    }
    return output.toString();
  }

  static char aliceChange(char ch) {
    if (ch == 'a') {
      return 'b';
    } else {
      return 'a';
    }
  }

  static char bobChange(char ch) {
    if (ch == 'z') {
      return 'y';
    } else {
      return 'z';
    }
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