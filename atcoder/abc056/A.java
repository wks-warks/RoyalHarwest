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
      char atcodeerCredibility = FR.next().charAt(0);
      char atcodeerReport = FR.next().charAt(0);
      char topcodeerCredibility = getTopcodeerCredibility(atcodeerCredibility, atcodeerReport);
      solution.append(topcodeerCredibility + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static char getTopcodeerCredibility(char atcodeerCredibility, char atcodeerReport) {
    if (atcodeerCredibility == 'H') {
      return atcodeerReport;
    }
    else {
      return (char) ('H' + 'D' - atcodeerReport);
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