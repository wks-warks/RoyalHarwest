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
      int sLen = FR.nextInt();
      String str = FR.next();

      Result result = Result.compute(str);
      solution.append(result.movesNeeded + "\n");
      solution.append(result.finalStr + "\n");
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

class Result {
  int movesNeeded;
  String finalStr;
  public Result(int movesNeeded, String finalStr) {
    this.movesNeeded = movesNeeded;
    this.finalStr = finalStr;
  }

  static Result compute(String str) {
    StringBuilder finalStr = new StringBuilder();
    int movesNeeded = 0;
    for (int i = 1; i < str.length(); i += 2) {
      if (str.charAt(i) == str.charAt(i-1)) {
        movesNeeded++;
        finalStr.append('a');
        finalStr.append('b');
      } else {
        finalStr.append(str.charAt(i-1));
        finalStr.append(str.charAt(i));
      }
    }
    return new Result(movesNeeded, finalStr.toString());

  }
}