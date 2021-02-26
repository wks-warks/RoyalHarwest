// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int islands = FR.nextInt();
    int boatServices = FR.nextInt();
    List<List<Integer>> serviceList = new ArrayList<List<Integer>>(islands);
    for (int i = 0; i < islands; i += 1) {
      serviceList.add(new ArrayList<Integer>());
    }
    for (int b = 0; b < boatServices; b += 1) {
      int first = FR.nextInt()-1;
      int second = FR.nextInt()-1;
      serviceList.get(first).add(second);
      serviceList.get(second).add(first);
    }

    boolean reachable = reachableInTwo(serviceList);
    solution.append(reachable ? "POSSIBLE" : "IMPOSSIBLE");
		PW.print(solution.toString());
    PW.close();
  }

  static boolean reachableInTwo(List<List<Integer>> serviceList) {
    int start = 0;
    for (var island : serviceList.get(start)) {
      if (island == serviceList.size()-1) {
        return true;
      }
      for (var nextNeighbour : serviceList.get(island)) {
        if (nextNeighbour == serviceList.size()-1) {
          return true;
        }
      }
    }
    return false;
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