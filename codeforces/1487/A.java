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
      int heroCount = FR.nextInt();
      List<Integer> heroLevels = new ArrayList<Integer>(heroCount);
      for (int h = 0; h < heroCount; h += 1) {
        heroLevels.add(FR.nextInt());
      }
      int winnerCount = getWinnerCount(heroLevels);
      solution.append(winnerCount + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getWinnerCount(List<Integer> heroLevels) {
    Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();
    int minimum = Integer.MAX_VALUE;
    for (var level : heroLevels) {
      if (!frequency.containsKey(level)) {
        frequency.put(level, 0);
      }
      frequency.put(level, frequency.get(level) + 1);
      minimum = Math.min(minimum, level);
    }

    return heroLevels.size() - frequency.get(minimum);
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