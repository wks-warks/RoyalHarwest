// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests;
    tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int arrLen = FR.nextInt();
      int swapPosLen = FR.nextInt();
      
      int[] arr = new int[arrLen];
      for (int i = 0; i < arrLen; i++) {
        arr[i] = FR.nextInt();
      }

      int[] swapPos = new int[swapPosLen];
      for (int i = 0; i < swapPosLen; i++) {
        swapPos[i] = FR.nextInt();
      }

      boolean sortable = isSortable(arr, swapPos);
      solution.append(sortable ? "YES\n" : "NO\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static boolean isSortable(int[] arr, int[] swapPos) {
    boolean[] inSwapPos = new boolean[arr.length];
    for (var idx : swapPos) {
      inSwapPos[idx-1] = true;
    }

    int prevMax = Integer.MIN_VALUE;
    int presMax = arr[0];
    for (int i = 1; i < arr.length; i++) {
      if (!inSwapPos[i-1]) {
        prevMax = presMax;
      }

      if (prevMax > arr[i]) {
        return false;
      }
      presMax = Math.max(presMax, arr[i]);
    }

    return true;
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

/*
1
5 2
3 4 4 2 5 
3 4
*/