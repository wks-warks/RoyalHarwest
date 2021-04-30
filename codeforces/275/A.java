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
      int[][] pressCount = new int[3][3];
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          pressCount[i][j] = FR.nextInt();
        }
      }

      int[][] finalState = getFinalState(pressCount);
      for (var row : finalState) {
        for (var col : row) {
          solution.append(col);
        }
        solution.append("\n");
      }
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int[][] getFinalState(int[][] pressCount) {
    int[][] state = new int[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        state[i][j] = 1;
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if ((pressCount[i][j] & 1) == 1) {
          toggle(i, j, state);
        }
      }
    }

    return state;
  }

  static void toggle(int i, int j, int[][] state) {
    int[][] toggleCoordinates = new int[][] {{i-1, j}, {i, j}, {i+1, j}, {i, j-1}, {i, j+1}};
    for (var coordinate : toggleCoordinates) {
      if (0 <= coordinate[0] && coordinate[0] < 3 && 0 <= coordinate[1] && coordinate[1] < 3) {
        state[coordinate[0]][coordinate[1]] = 1 - state[coordinate[0]][coordinate[1]];
      }
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