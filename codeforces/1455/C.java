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
      int aliceStamina = FR.nextInt();
      int bobStamina = FR.nextInt();

      Result result = Result.compute(aliceStamina, bobStamina);
      solution.append(result.toString());
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
  int aliceWins;
  int bobWins;

  public Result(int aliceWins, int bobWins) {
    this.aliceWins = aliceWins;
    this.bobWins = bobWins;
  }

  static Result compute(int aliceStamina, int bobStamina) {
    return new Result(aliceStamina - 1, bobStamina);
  }
  
  @Override
  public String toString() {
    return String.format("%d %d\n", aliceWins, bobWins);
  }
}