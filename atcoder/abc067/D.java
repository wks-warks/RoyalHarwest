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
      int cells = FR.nextInt();
      List<List<Integer>> adjacentCells = new ArrayList<List<Integer>>(cells);
      for (int c = 0; c < cells; c += 1) {
        adjacentCells.add(new ArrayList<Integer>());
      }
      int roads = cells - 1;
      for (int r = 0; r < roads; r += 1) {
        int firstCell = FR.nextInt() - 1;
        int secondCell = FR.nextInt() - 1;
        adjacentCells.get(firstCell).add(secondCell);
        adjacentCells.get(secondCell).add(firstCell);
      }

      String winner = getWinner(cells, adjacentCells);
      solution.append(winner + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static String getWinner(int cells, List<List<Integer>> adjacentCells) {
    Deque<Integer> path = getPath(0, cells-1, adjacentCells, new ArrayList<Boolean>(Collections.nCopies(cells, false)));

    List<Integer> colors = new ArrayList<Integer>(Collections.nCopies(cells, 0));
    colors.set(0, -1); // Black
    colors.set(cells-1, 1); // White
    for (int i = 0; path.size() > 0; i += 1) {
      if (isEven(i)) {
        colors.set(path.poll(), -1);
      } else {
        colors.set(path.pollLast(), 1);
      }
    }


    for (int c = 0; c < cells; c += 1) {
      if (colors.get(c) != 0) {
        paintNeighbours(c, colors.get(c), adjacentCells, colors);
      }      
    }

    int blackCount = countOccurrences(colors, -1);
    int whiteCount = cells - blackCount;
    return blackCount > whiteCount ? "Fennec" : "Snuke";
  }

  static void paintNeighbours(int city, int color, List<List<Integer>> adjacentCells, List<Integer> colors) {
    for (var neighbour : adjacentCells.get(city)) {
      if (colors.get(neighbour) == 0) {
        colors.set(neighbour, color);
        paintNeighbours(neighbour, color, adjacentCells, colors);
      }
    }
  }
  
  static Deque<Integer> getPath(int start, int end, List<List<Integer>> adjacentCells, List<Boolean> visited) {
    visited.set(start, true);
    for (var neighbour : adjacentCells.get(start)) {
      if (visited.get(neighbour)) {
        continue;
      }
      if (neighbour == end) {
        return new ArrayDeque<Integer>();
      } else {
        Deque<Integer> path = getPath(neighbour, end, adjacentCells, visited);
        if (path != null) {
          path.addFirst(neighbour);
          return path;
        }
      }
    }
    return null;
  }

  static int countOccurrences(List<Integer> list, int desired) {
    int count = 0;
    for (var value : list) {
      if (value == desired) {
        count += 1;
      }
    }
    return count;
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