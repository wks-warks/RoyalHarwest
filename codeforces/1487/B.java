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
      int spots = FR.nextInt();
      int findAt = FR.nextInt();
      int position = getPosition(spots, findAt);
      solution.append(position + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getPosition(int spots, int findAt) {
    if (isEven(spots)) {
      return (findAt - 1) % spots + 1;
    } else {
      int collisions = getCollisions(spots, findAt);
      return (findAt - 1 + collisions) % spots + 1;
    }
  }

  static int getCollisions(int spots, int findAt) {
    int period = spots>>1;
    return (findAt-1) / period;
  }

  static boolean isEven(int num) {
    return (num & 1) == 0;
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