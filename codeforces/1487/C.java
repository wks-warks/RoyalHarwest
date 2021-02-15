// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  static Triple required;
  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int teams = FR.nextInt();
      required = new Triple();
      required.wins = (teams-1)>>1;
      required.losses = required.wins;
      required.draws = teams-1 - required.wins - required.losses;
      List<List<Integer>> results = getResults(required, teams);
      for (int i = 0; i < teams; i += 1) {
        for (int j = i + 1; j < teams; j += 1) {
          solution.append(results.get(i).get(j) + " ");
        }
      }
      solution.append("\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static List<List<Integer>> getResults(Triple required, int teams) {
    List<List<Integer>> results = new ArrayList<List<Integer>>(teams);
    for (int t = 0; t < teams; t += 1) {
      results.add(new ArrayList<Integer>(Collections.nCopies(teams, 0)));
    }
    if (required.draws > 0) {
      for (int i = 0; i < teams; i += 1) {
        for (int j = i + 1; j < teams; j += 1) {
          if (i % 2 == 0 && j == i + 1) {
            results.get(i).set(j, 0);
          } else {
            if ((i&1) != (j&1)) {
              results.get(i).set(j, 1);
            } else {
              results.get(i).set(j, -1);
            }
          }
        }
      }
    } else {
      for (int i = 0; i < teams; i += 1) {
        for (int j = i + 1; j < teams; j += 1) {
          if ((i&1) == (j&1)) {
            results.get(i).set(j, -1);
          } else {
            results.get(i).set(j, 1);
          }
        }
      }
    }
    return results;
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

class Triple {
  int wins;
  int draws;
  int losses;
}