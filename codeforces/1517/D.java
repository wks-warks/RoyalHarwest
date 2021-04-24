// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int rows = FR.nextInt();
    int cols = FR.nextInt();
    int moves = FR.nextInt();

    int[][] horizontalEdgeWeights = new int[rows][cols-1];

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols - 1; c++) {
        horizontalEdgeWeights[r][c] = FR.nextInt();
      }
    }

    int[][] verticalEdgeWeights = new int[rows-1][cols];
    for (int r = 0; r < rows - 1; r++) {
      for (int c = 0; c < cols; c++) {
        verticalEdgeWeights[r][c] = FR.nextInt();
      }
    }

    int[][] result = getResult(rows, cols, moves, horizontalEdgeWeights, verticalEdgeWeights);
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        solution.append(result[r][c] + " ");
      }
      solution.append("\n");
    }

		PW.print(solution.toString());
    PW.close();
  }

  static int[][] getResult(int rows, int cols, int moves, int[][] horizontalEdgeWeights, int[][] verticalEdgeWeights) {
    int[][] result = new int[rows][cols];
    if ((moves & 1) == 1) {
      for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
          result[r][c] = -1;
        }
      }
  
      return result;
    }

    int mid = moves >> 1;
    int[][][] minForDistance = new int[rows][cols][mid+1];
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        for (int m = 1; m <= mid; m++) {
          minForDistance[r][c][m] = Integer.MAX_VALUE;
        }
      }
    }

    for (int m = 1; m <= mid; m++) {
      for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
          int minBoredom = minForDistance[r][c][m];

          if (r > 0) {
            int candidateBoredom = minForDistance[r-1][c][m-1] + verticalEdgeWeights[r-1][c];
            minBoredom = Math.min(minBoredom, candidateBoredom);
          }

          if (c > 0) {
            int candidateBoredom = minForDistance[r][c-1][m-1] + horizontalEdgeWeights[r][c-1];
            minBoredom = Math.min(minBoredom, candidateBoredom);
          }

          if (r + 1 < rows) {
            int candidateBoredom = minForDistance[r+1][c][m-1] + verticalEdgeWeights[r][c];
            minBoredom = Math.min(minBoredom, candidateBoredom);
          }

          if (c + 1 < cols) {
            int candidateBoredom = minForDistance[r][c+1][m-1] + horizontalEdgeWeights[r][c];
            minBoredom = Math.min(minBoredom, candidateBoredom);
          }

          minForDistance[r][c][m] = minBoredom;
        }
      }
    }

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        result[r][c] = minForDistance[r][c][mid] << 1;
      }
    }

    return result;
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