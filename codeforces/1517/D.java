// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException {
    StringBuilder solution = new StringBuilder();
    int rows = FR.nextInt();
    int cols = FR.nextInt();
    int moves = FR.nextInt();

    Map<Integer, Integer> horizontalEdgeWeights = new HashMap<Integer, Integer>();
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols - 1; c++) {
        int hash = getHash(r, c);
        horizontalEdgeWeights.put(hash, FR.nextInt());
      }
    }

    Map<Integer, Integer> verticalEdgeWeights = new HashMap<Integer, Integer>();
    for (int r = 0; r < rows - 1; r++) {
      for (int c = 0; c < cols; c++) {
        int hash = getHash(r, c);
        verticalEdgeWeights.put(hash, FR.nextInt());
      }
    }
    
    List<List<Integer>> result = getResult(rows, cols, moves, horizontalEdgeWeights, verticalEdgeWeights);
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        int value = (result != null ? result.get(r).get(c) : -1);
        solution.append(value + " ");
      }
      solution.append("\n");
    }

		BW.write(solution.toString());
    BW.close();
  }

  static List<List<Integer>> getResult(int rows, int cols, int moves, Map<Integer, Integer> horizontalEdgeWeights, Map<Integer, Integer> verticalEdgeWeights) {
    if ((moves & 1) == 1) {
      return null;
    }

    int mid = moves >> 1;
    List<List<List<Integer>>> minForDistance = new ArrayList<List<List<Integer>>>(rows);
    for (int r = 0; r < rows; r++) {
      minForDistance.add(new ArrayList<List<Integer>>(cols));

      for (int c = 0; c < cols; c++) {
        minForDistance.get(r).add(new ArrayList<Integer>(Collections.nCopies(mid+1, Integer.MAX_VALUE)));
        minForDistance.get(r).get(c).set(0, 0);
      }
    }

    for (int m = 1; m <= mid; m++) {
      for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
          int minBoredom = minForDistance.get(r).get(c).get(m);
          int hash = getHash(r, c);

          if (r > 0) {
            if (minForDistance.get(r-1).get(c).get(m-1) < Integer.MAX_VALUE) {
              int candidateBoredom = minForDistance.get(r-1).get(c).get(m-1) + verticalEdgeWeights.get(getHash(r-1, c));
              minBoredom = Math.min(minBoredom, candidateBoredom);
            }
          }

          if (c > 0) {
            if (minForDistance.get(r).get(c-1).get(m-1) < Integer.MAX_VALUE) {
              int candidateBoredom = minForDistance.get(r).get(c-1).get(m-1) + horizontalEdgeWeights.get(getHash(r, c-1));
              minBoredom = Math.min(minBoredom, candidateBoredom);
            }
          }
          
          if (r + 1 < rows) {
            if (minForDistance.get(r+1).get(c).get(m-1) < Integer.MAX_VALUE) {
              int candidateBoredom = minForDistance.get(r+1).get(c).get(m-1) + verticalEdgeWeights.get(hash);
              minBoredom = Math.min(minBoredom, candidateBoredom);
            }
          }
   
          if (c + 1 < cols) {
            if (minForDistance.get(r).get(c+1).get(m-1) < Integer.MAX_VALUE) {
              int candidateBoredom = minForDistance.get(r).get(c+1).get(m-1) + horizontalEdgeWeights.get(hash);
              minBoredom = Math.min(minBoredom, candidateBoredom);
            }
          }

          minForDistance.get(r).get(c).set(m, minBoredom);
        }
      }
    }

    List<List<Integer>> result = new ArrayList<List<Integer>>(rows);
    for (int r = 0; r < rows; r++) {
      result.add(new ArrayList<Integer>(cols));

      for (int c = 0; c < cols; c++) {
        result.get(r).add(minForDistance.get(r).get(c).get(mid) << 1);
      }
    }

    return result;
  }

  static int getHash(int row, int col) {
    return (row * 1000 + col);
  }
  static int getRow(int hash) {
    return hash / 1000;
  }
  static int getCol(int hash) {
    return hash % 1000;
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