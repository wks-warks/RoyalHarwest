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
      int checkPoints = FR.nextInt();
      int runners = FR.nextInt();
      int alternatives = runners;

      List<List<Integer>> pathLengths = new ArrayList<List<Integer>>(checkPoints);
      for (int c = 0; c < checkPoints; c++) {
        pathLengths.add(new ArrayList<Integer>(runners));
        for (int a = 0; a < alternatives; a++) {
          int pLength = FR.nextInt();
          pathLengths.get(c).add(pLength);
        }
      }

      List<List<Integer>> pathsAssigned = getPathsAssigned(checkPoints, runners, alternatives, pathLengths);
      for (var list : pathsAssigned) {
        for (var len : list) {
          solution.append(len + " ");
        }
        solution.append("\n");
      }
    }
		PW.print(solution.toString());
    PW.close();
  }

  static List<List<Integer>> getPathsAssigned(int checkPoints, int runners, int alternatives, List<List<Integer>> pathLengths) {
    PriorityQueue<Route> shortestPath = new PriorityQueue<Route>(new Route());
    for (int c = 0; c < checkPoints; c++) {
      for (int a = 0; a < alternatives; a++) {
        shortestPath.add(new Route(pathLengths.get(c).get(a), c, a));
      }
    }

    List<List<Integer>> pathsAssigned = new ArrayList<List<Integer>>(checkPoints);
    for (int c = 0; c < checkPoints; c++) {
      pathsAssigned.add(new ArrayList<Integer>(Collections.nCopies(runners, -1)));
    }
    
    for (int r = 0; r < runners; r++) {
      Route nextBest = shortestPath.poll();
      int len = nextBest.getLen();
      int c = nextBest.getCIdx();
      int a = nextBest.getAIdx();

      pathsAssigned.get(c).set(r, len);
      // if (c == 0 && a == 0)
      // System.out.println("RUNNER " + r);
      // System.out.println("PATHS ASSIGNED " + pathsAssigned.get(c).get(r));
      // System.out.println(" C "  + c + " R " + r + " L " + len);
      pathLengths.get(c).set(a, -1); // i.e used
    }

    // StringBuilder solution = new StringBuilder();
    // for (var list : pathsAssigned) {
    //   for (var len : list) {
    //     solution.append(len + " ");
    //   }
    //   solution.append("\n");
    // }
    // System.out.println(solution.toString());

    // StringBuilder nS = new StringBuilder();
    // for (var list : pathLengths) {
    //   for (var len : list) {
    //     nS.append(len + " ");
    //   }
    //   nS.append("\n");
    // }
    // System.out.println(nS.toString());
    // System.exit(0);

    for (int c = 0; c < checkPoints; c++) {
      int r = 0;
      for (int a = 0; a < alternatives; a++) {
        if (pathLengths.get(c).get(a) == -1) {
          // System.out.println("SKIPPED " + c + " " + a);
          continue;
        }
        // if (r == 3) {
        //   System.out.println("Start");
        //   StringBuilder solution = new StringBuilder();
        //   for (var list : pathsAssigned) {
        //     for (var len : list) {
        //       solution.append(len + " ");
        //     }
        //     solution.append("\n");
        //   }
        //   System.out.print(solution.toString());
        //   System.out.println("End");
        //   System.exit(0);
        // }
        while (pathsAssigned.get(c).get(r) != -1) {
          r++;
        }
        // if (c == 0 && a == 0) {
          // System.out.println("RUNNER " + r);
        // }
        // System.out.println(c + "c  a" + a + " r" + r + " l" + pathLengths.get(c).get(a));
        pathsAssigned.get(c).set(r++, pathLengths.get(c).get(a));
        // System.out.println("r " + (r-1) + " assn" + pathsAssigned.get(c).get(r-1));
      }
    }

    return pathsAssigned;
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

class Route implements Comparator<Route> {
  private int len, cIdx, aIdx;

  public Route() {

  }
  public Route(int len, int cIdx, int aIdx) {
    this.len = len;
    this.cIdx = cIdx;
    this.aIdx = aIdx;
  }

  public int getLen() {
    return len;
  }
  public int getCIdx() {
    return cIdx;
  }
  public int getAIdx() {
    return aIdx;
  }

  @Override
  public int compare(Route first, Route second) {
    if (first.getLen() < second.getLen()) {
      return -1;
    } else if (first.getLen() == second.getLen()) {
      return 0;
    } else {
      return 1;
    }
  }
}

/*

1
2 3
2 3 4
1 3 5

*/