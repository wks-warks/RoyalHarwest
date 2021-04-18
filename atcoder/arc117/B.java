// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
		int buildings = FR.nextInt();
    List<Integer> heights = new ArrayList<Integer>(buildings);
    for (int b = 0; b < buildings; b++) {
      heights.add(FR.nextInt());
    }

    int states = getStates(heights);
    PW.print(states);
    PW.close();
  }

  static int getStates(List<Integer> heights) {
    Set<Integer> distinctHeights = new TreeSet<Integer>(heights);
    int states = 1;
    int prev = 0;
    for (var height : distinctHeights) {
      int diff = height - prev;
      int multiplier = diff + 1;
      states = (int) (((long) multiplier * states) % 1_000_000_007);
      prev = height;
    }
    return states;
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

