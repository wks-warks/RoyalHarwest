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
      int n = FR.nextInt();
      int k = FR.nextInt();
      
      int[] messageIds = new int[n];
      for (int i = 0; i < n; i++) {
        messageIds[i] = FR.nextInt();
      }
      int[] finalScreen = getFinalScreen(messageIds, k);
      solution.append(finalScreen.length + "\n");
      for (var id : finalScreen) {
        solution.append(id + " ");
      }
      solution.append("\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int[] getFinalScreen(int[] messageIds, int k) {
    Queue<Integer> screen = new LinkedList<Integer>();
    Set<Integer> idsOnScreen = new HashSet<Integer>();

    for (int i = 0; i < messageIds.length; i++) {
      if (idsOnScreen.contains(messageIds[i])) {
        continue;
      }

      if (screen.size() == k) {
        int removedID = screen.poll();
        idsOnScreen.remove(removedID);
      }
      screen.add(messageIds[i]);
      idsOnScreen.add(messageIds[i]);
    }

    int[] finalScreen = new int[screen.size()];
    for (int i = finalScreen.length-1; i >= 0; i--) {
      int id = screen.poll();
      finalScreen[i] = id;
    }
    return finalScreen;
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