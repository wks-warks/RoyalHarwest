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
      int listSize = FR.nextInt();
      int operationsAllowed = FR.nextInt();

      List<Integer> list = new ArrayList<Integer>(listSize);
      for (int i = 0; i < listSize; i++) {
        list.add(FR.nextInt());
      }

      List<Integer> result = getResult(list, operationsAllowed);
      for (var num : result) {
        solution.append(num + " ");
      }
      solution.append("\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static List<Integer> getResult(List<Integer> list, int operationsAllowed) {
    List<Integer> result = new ArrayList<Integer>(list.size());
    int operationsDone = 0;

    for (int i = 0; i < list.size(); i++) {
      int change = Math.min(operationsAllowed - operationsDone, list.get(i));
      result.add(list.get(i) - change);
      operationsDone += change;
    }

    result.set(result.size() - 1, result.get(result.size() - 1) + operationsDone);
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