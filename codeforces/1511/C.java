// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int cardCount = FR.nextInt();
    int queries = FR.nextInt();

    List<Integer> firstPos = new ArrayList<Integer>(Collections.nCopies(50, -1));

    for (int c = 0; c < cardCount; c += 1) {
      int colour = FR.nextInt();
      if (firstPos.get(colour - 1).equals(-1)) {
        firstPos.set(colour - 1, c + 1);
      }
    }

    for (int q = 0; q < queries; q += 1) {
      int colour = FR.nextInt();
      int pos = firstPos.get(colour - 1);

      for (int clr = 0; clr < 50; clr += 1) {
        if (firstPos.get(clr) < pos) {
          firstPos.set(clr, firstPos.get(clr) + 1);
        }
      }
      firstPos.set(colour - 1, 1);
      solution.append(pos + " ");
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